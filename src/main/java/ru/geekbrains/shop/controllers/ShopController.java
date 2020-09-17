package ru.geekbrains.shop.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import ru.geekbrains.shop.beans.Cart;
import ru.geekbrains.shop.services.ProductService;
import ru.geekbrains.shop.services.ShopuserService;
import ru.geekbrains.shop.utils.CaptchaGenerator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final Cart cart;
    private final CaptchaGenerator captchaGenerator;
    private final ProductService productService;
    private final ShopuserService shopuserService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) Integer category) {
        model.addAttribute("cart", cart.getCartRecords());
        model.addAttribute("products", productService.getAll(category));
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, @CookieValue(value = "data", required = false) String data, Principal principal) {

        if (principal == null) {
            return "redirect:/";
        }

        if (data != null) {
            System.out.println(data);
        }
        model.addAttribute("products", productService.getAll(null));
        return "admin";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, @CookieValue(value = "data", required = false) String data, Principal principal) {

        if (principal == null) {
            return "redirect:/";
        }

        shopuserService.findByPhone(principal.getName())
            .ifPresent(shopuser -> model.addAttribute("shopuser", shopuser));
        if (data != null) {
            System.out.println(data);
        }
        return "profile";
    }

    @GetMapping(value = "/captcha", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getCaptcha(HttpSession session) {
        try {
            BufferedImage image = captchaGenerator.getCaptchaImage();
            session.setAttribute("captchaCode", captchaGenerator.getCaptchaString());
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bao);
            return bao.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}