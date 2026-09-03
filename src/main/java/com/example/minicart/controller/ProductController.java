package com.example.minicart.controller;

import com.example.minicart.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String products(Model model) {
        model.addAttribute("pageTitle", "Products");
        model.addAttribute("products",productService.getAllProducts());
        return "products";
    }
}
