package com.example.minicart.service;

import com.example.minicart.entity.Order;
import com.example.minicart.entity.OrderItem;
import com.example.minicart.model.CartItem;
import com.example.minicart.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService  cartService;

    public OrderService (OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    public void CheckOut() {
        Order order = new Order();
        order.setTotal(cartService.getTotal());

        OrderItem orderItem = new OrderItem();

        for(CartItem cartItem : cartService.getCartItem()) {
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());

            order.getItems().add(orderItem);
        }
    }
}
