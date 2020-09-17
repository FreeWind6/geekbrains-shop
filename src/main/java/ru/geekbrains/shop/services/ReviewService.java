package ru.geekbrains.shop.services;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ru.geekbrains.shop.persistence.entities.Product;
import ru.geekbrains.shop.persistence.entities.Review;
import ru.geekbrains.shop.persistence.entities.Shopuser;
import ru.geekbrains.shop.persistence.repositories.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {


    private final ReviewRepository reviewRepository;

    public List<Review> getReviewsByProduct(Product product) {
        return reviewRepository.findByProduct(product);
    }

    public List<Review> getReviewsByShopuser(Shopuser shopuser) {
        return reviewRepository.findByShopuser(shopuser);
    }

    public void save(Review review) {
        reviewRepository.save(review);
    }

}