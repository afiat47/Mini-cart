package com.example.minicart.service;

import com.example.minicart.entity.Product;
import com.example.minicart.model.CartItem;
import org.springframework.stereotype.Service;
import com.example.minicart.model.CartItem;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
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

    public Collection<CartItem> getCartItem() {
        return cart.values();
    }

    public Double getTotal() {
        Double total = 0.0;
        for(CartItem item: cart.values()) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void increaseQuantity(Long productId) {
        if(cart.containsKey(productId)) {
            cart.get(productId).increaseQuantity();
        }
    }

    public void decreaseQuantity(Long productId) {
        if(cart.containsKey(productId)) {
            CartItem item = cart.get(productId);

            if (item.getQuantity() > 1) {
                item.decreaseQuantity();
            } else {
                cart.remove(productId);
            }
        }
    }

    public void removeItem(Long productId) {
        cart.remove(productId);
    }

    public int cartItemCount() {
        int count = 0;
        for(CartItem item : cart.values()) {
            count = count + item.getQuantity();
        }

        return count;
    }

    public void clearCart() {
        cart.clear();
    }
}
