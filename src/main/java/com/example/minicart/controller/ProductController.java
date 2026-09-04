package com.example.minicart.controller;

import com.example.minicart.entity.Product;
import com.example.minicart.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/new")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("edit/{id}")
    public String showEditFrom(@PathVariable Long id, Model model) {
        Product product = productService.getProductByID(id);
        model.addAttribute("product",product);
        return "product-form";
    }
}
