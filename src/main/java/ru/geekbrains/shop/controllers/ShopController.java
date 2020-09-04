package ru.geekbrains.shop.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geekbrains.shop.persistence.entities.enums.ProductCategory;
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

    @GetMapping("/{id}") //TODO /?category=0
    public String index(Model model, @PathVariable ProductCategory id) {
        model.addAttribute("products", productService.getAllByCategory(id));
        return "index";
    }

}
