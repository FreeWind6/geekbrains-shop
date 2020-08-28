package ru.geekbrains.shop.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.geekbrains.shop.persistence.entities.Product;
import ru.geekbrains.shop.persistence.entities.enums.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategory(ProductCategory category);
}