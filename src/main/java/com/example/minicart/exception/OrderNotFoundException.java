package com.example.minicart.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("Order with ID " + id + " not found.");
    }

}
