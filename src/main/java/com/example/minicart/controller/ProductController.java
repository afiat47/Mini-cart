package com.example.minicart.controller;

import com.example.minicart.entity.Product;
import com.example.minicart.service.CartService;
import com.example.minicart.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    public ProductController(ProductService productService, CartService cartService) {

        this.productService = productService;
        this.cartService = cartService;
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

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam String item, Model model) {
        model.addAttribute("PageTitle", "Products");
        model.addAttribute("products",productService.searchProduct(item));
        return "products";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id) {
        Product product = productService.getProductByID(id);
        cartService.addToCart(product);
        return "redirect:/products";
    }
}
