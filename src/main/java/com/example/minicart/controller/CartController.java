package com.example.minicart.controller;

import org.springframework.ui.Model;
import com.example.minicart.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems",cartService.getCartItem());
        model.addAttribute("total",cartService.getTotal());

        return "cart";
    }

    @PostMapping("/cart/increase/{id}")
    public String increaseQuantity(@PathVariable Long id) {
        cartService.increaseQuantity(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/decrease/{id}")
    public String decreaseQuantity(@PathVariable Long id) {
        cartService.decreaseQuantity(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
        return "redirect:/cart";
    }

}
