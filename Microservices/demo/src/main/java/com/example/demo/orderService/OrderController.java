package com.example.demo.orderService;

import com.example.demo.userService.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        if (userRepository.existsById(order.getUserId())) {
            // Перевірка наявності продукту
            Boolean productExists = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8080/products/name/" + order.getProduct())
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if (productExists != null && productExists) {
                return orderRepository.save(order);
            } else {
                throw new RuntimeException("Product not found");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
}