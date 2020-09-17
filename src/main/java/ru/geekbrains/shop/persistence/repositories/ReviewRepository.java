package ru.geekbrains.shop.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.geekbrains.shop.persistence.entities.Product;
import ru.geekbrains.shop.persistence.entities.Review;
import ru.geekbrains.shop.persistence.entities.Shopuser;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByProduct(Product product);
    List<Review> findByShopuser(Shopuser shopuser);
}