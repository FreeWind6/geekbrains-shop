package ru.geekbrains.shop.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.shop.services.ProductService;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) Integer category) {
        model.addAttribute("products", productService.getAll(category));
        return "index";
    }

}
