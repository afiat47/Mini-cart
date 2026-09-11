package com.example.minicart.dto;

import com.example.minicart.model.CartItem;

import java.util.Collection;

public class CartResponse {

    private Collection<CartItem> items;
    private Double total;
    private int count;

    public CartResponse(Collection<CartItem> items, Double total, int count) {
        this.items = items;
        this.total = total;
        this.count = count;
    }

    public Collection<CartItem> getItems() { return items; }

    public Double getTotal() { return total; }

    public int getCount() { return count; }
}
