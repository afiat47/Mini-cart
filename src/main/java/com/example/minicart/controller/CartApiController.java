package com.example.minicart.controller;

import com.example.minicart.dto.CartResponse;
import com.example.minicart.entity.Product;
import com.example.minicart.service.CartService;
import com.example.minicart.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartApiController {

    private final CartService cartService;
    private final ProductService productService;

    public CartApiController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public CartResponse getCart() {
        return cartResponse();
    }

    @PostMapping("/items/{productId}")
    public CartResponse addToCart(@PathVariable Long productId) {
        Product product = productService.getProductByID(productId);
        cartService.addToCart(product);
        return cartResponse();
    }

    @PatchMapping("/items/{productId}/increase")
    public CartResponse increaseQuantity(@PathVariable Long productId) {
        cartService.increaseQuantity(productId);
        return cartResponse();
    }

    @PatchMapping("/items/{productId}/decrease")
    public CartResponse decreaseQuantity(@PathVariable Long productId) {
        cartService.decreaseQuantity(productId);
        return cartResponse();
    }

    @DeleteMapping("/items/{productId}")
    public CartResponse removeItem(@PathVariable Long productId) {
        cartService.removeItem(productId);
        return cartResponse();
    }

    @DeleteMapping
    public CartResponse clearCart() {
        cartService.clearCart();
        return cartResponse();
    }

    private CartResponse cartResponse() {
        return new CartResponse(
                cartService.getCartItem(),
                cartService.getTotal(),
                cartService.cartItemCount()
        );
    }
}
