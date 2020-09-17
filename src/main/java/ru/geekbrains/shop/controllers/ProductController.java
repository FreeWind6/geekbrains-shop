package ru.geekbrains.shop.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

import ru.geekbrains.shop.dto.ProductDto;
import ru.geekbrains.shop.dto.ReviewDto;
import ru.geekbrains.shop.exceptions.ProductNotFoundException;
import ru.geekbrains.shop.persistence.entities.Image;
import ru.geekbrains.shop.persistence.entities.Product;
import ru.geekbrains.shop.persistence.entities.Review;
import ru.geekbrains.shop.persistence.entities.Shopuser;
import ru.geekbrains.shop.services.ImageService;
import ru.geekbrains.shop.services.ProductService;
import ru.geekbrains.shop.services.ReviewService;
import ru.geekbrains.shop.services.ShopuserService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ImageService imageService;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final ShopuserService shopuserService;

    @GetMapping("/{id}")
    public String getOne(Model model, @PathVariable String id) {

        Optional<Product> productOptional = productService.getOneById(UUID.fromString(id));

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            List<Review> reviews = reviewService.getReviewsByProduct(product);
            model.addAttribute("product", product);
            model.addAttribute("reviews", reviews);
        }

        return "product";
    }

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String id) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = imageService.loadFileAsResource(id);
        if (bufferedImage != null) {
            ImageIO.write(bufferedImage,"png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } else {
            return new byte[0];
        }
    }

    @PostMapping
    public String addOne(@RequestParam("image") MultipartFile image, ProductDto productDto) throws IOException {
        Image img = imageService.uploadImage(image, productDto.getTitle());
        return productService.save(productDto, img);
    }

    @PostMapping("/reviews")
    public String addReview(ReviewDto reviewDto, HttpSession httpSession, Principal principal) {

        Optional<Product> productOptional = productService.getOneById(reviewDto.getProductId());
        Optional<Shopuser> shopuserOptional = shopuserService.findByPhone(principal.getName());

        Product product;

        if (productOptional.isPresent() && shopuserOptional.isPresent()) {
            product = productOptional.get();
            Review review = Review.builder()
                .commentary(reviewDto.getCommentary())
                .product(product)
                .shopuser(shopuserOptional.get())
            .build();

            reviewService.save(review);

            return "redirect:/products/" + product.getId();
        }

        return "redirect:/";

    }

}