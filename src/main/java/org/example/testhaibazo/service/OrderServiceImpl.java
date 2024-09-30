package org.example.testhaibazo.service;

import org.example.testhaibazo.dto.OrderDTO;
import org.example.testhaibazo.model.Order;
import org.example.testhaibazo.model.Product;
import org.example.testhaibazo.model.User;
import org.example.testhaibazo.repository.OrderRepository;
import org.example.testhaibazo.repository.ProductRepository;
import org.example.testhaibazo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDto) {
        Order order = convertToEntity(orderDto);
        return convertToDto(orderRepository.save(order));
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDto) {
        return orderRepository.findById(id).map(order -> {
            order.setTotalPrice(orderDto.getTotalPrice());
            order.setOrderDate(orderDto.getOrderDate());
            User user = userRepository.findById(orderDto.getUserId()).orElse(null);
            order.setUser(user);

            List<Product> products = productRepository.findAllById(orderDto.getProductIds());
            order.setProducts(products);

            return convertToDto(orderRepository.save(order));
        }).orElse(null);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDto(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getOrderDate(),
                order.getTotalPrice(),
                order.getUser() != null ? order.getUser().getId() : null,
                order.getProducts().stream().map(Product::getId).collect(Collectors.toList())
        );
    }

    private Order convertToEntity(OrderDTO orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setOrderDate(orderDto.getOrderDate());

        User user = userRepository.findById(orderDto.getUserId()).orElse(null);
        order.setUser(user);


        return order;
    }
}
