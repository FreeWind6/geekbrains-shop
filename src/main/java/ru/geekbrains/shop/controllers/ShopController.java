package ru.geekbrains.shop.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.geekbrains.shop.services.ProductService;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productService.getAll());
        return "index";
    }

}
