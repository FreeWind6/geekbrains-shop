package ru.geekbrains.shop.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import ru.geekbrains.shop.persistence.entities.enums.ProductCategory;
import ru.geekbrains.shop.persistence.entities.utils.PersistableEntity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends PersistableEntity {

    private String title;
    private String description;
    private Date added;
    private Double price;
    private boolean available;

    @Enumerated
    private ProductCategory category;

    @OneToOne
    @JoinColumn(name = "image")
    private Image image;

}