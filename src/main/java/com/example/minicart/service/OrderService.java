package com.example.minicart.service;

import com.example.minicart.entity.Order;
import com.example.minicart.entity.OrderItem;
import com.example.minicart.exception.EmptyCartException;
import com.example.minicart.exception.OrderNotFoundException;
import com.example.minicart.model.CartItem;
import com.example.minicart.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService  cartService;

    public OrderService (OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    public Order CheckOut() {
        if (cartService.getCartItem().isEmpty()) {
            throw new EmptyCartException();
        }

        Order order = new Order();
        order.setTotal(cartService.getTotal());

        for(CartItem cartItem : cartService.getCartItem()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());

            order.getItems().add(orderItem);
        }

        Order saved = orderRepository.save(order);
        cartService.clearCart();
        return saved;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }
}
