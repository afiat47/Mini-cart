package com.example.minicart.controller;

import com.example.minicart.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String orderHistory(Model model) {

        model.addAttribute("orders", orderService.getAllOrders());

        return "orders";
    }
}