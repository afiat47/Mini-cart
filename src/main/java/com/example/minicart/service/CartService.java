package com.example.minicart.service;

import com.example.minicart.entity.Product;
import com.example.minicart.model.CartItem;
import org.springframework.stereotype.Service;
import com.example.minicart.model.CartItem;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    private final Map<Long, CartItem> cart = new HashMap<>();

    public void addToCart(Product product) {
        Long productId  = product.getId();

        if(cart.containsKey(productId)) {
            cart.get(productId).increaseQuantity();
        }
        else {
            cart.put(productId, new CartItem(product,1));
        }
    }
}
