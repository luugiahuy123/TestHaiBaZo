package org.example.testhaibazo.service;

import org.example.testhaibazo.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO> getAllOrders();

    Optional<OrderDTO> getOrderById(Long id);

    OrderDTO createOrder(OrderDTO orderDto);

    OrderDTO updateOrder(Long id, OrderDTO orderDto);

    void deleteOrder(Long id);
}
