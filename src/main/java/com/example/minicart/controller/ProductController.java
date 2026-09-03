package com.example.minicart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String products(Model model) {
        model.addAttribute("pageTitle", "Products");
        return "products";
    }
}
