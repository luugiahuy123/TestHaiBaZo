package org.example.testhaibazo.data;

import jakarta.annotation.PostConstruct;
import org.example.testhaibazo.model.*;
import org.example.testhaibazo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class DataLayer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostConstruct
    public void loadData() {

        User user1 = new User();
        user1.setUsername("Huy");
        user1.setPassword("123");
        user1.setEmail("luugiahuyqn@gmail.com");
        user1.setAddress("DN");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("LuuGiaHuy");
        user2.setPassword("456");
        user2.setEmail("luugiahuyqn@gmail.com.com");
        user2.setAddress("QN");
        userRepository.save(user2);

        Category category1 = new Category();
        category1.setName("Skincare");
        category1.setDescription("Products for skin care");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("Makeup");
        category2.setDescription("Cosmetic products for makeup");
        categoryRepository.save(category2);

        Product product1 = new Product();
        product1.setName("Moisturizer");
        product1.setPrice(100.0);
        product1.setDescription("Hydrating moisturizer for all skin types.");
        product1.setStock(100);
        product1.setSize("N/A");
        product1.setColor("White");
        product1.setCategory(category1);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Lipstick");
        product2.setPrice(120.2);
        product2.setDescription("Long-lasting lipstick in various shades.");
        product2.setStock(50);
        product2.setSize("N/A");
        product2.setColor("Red");
        product2.setCategory(category2);
        productRepository.save(product2);

        Order order1 = new Order();
        order1.setTotalPrice(39.98);
        order1.setOrderDate(LocalDate.now());
        order1.setUser(user1);
        order1.setProducts(List.of(product1, product2));
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setTotalPrice(19.99);
        order2.setOrderDate(LocalDate.now());
        order2.setUser(user2);
        order2.setProducts(List.of(product1));
        orderRepository.save(order2);

        Review review1 = new Review();
        review1.setRating(5);
        review1.setComment("Excellent moisturizer!");
        review1.setProduct(product1);
        review1.setUser(user1);
        reviewRepository.save(review1);

        Review review2 = new Review();
        review2.setRating(4);
        review2.setComment("Good lipstick but could be more vibrant.");
        review2.setProduct(product2);
        review2.setUser(user2);
        reviewRepository.save(review2);


    }
}
