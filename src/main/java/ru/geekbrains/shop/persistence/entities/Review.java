package ru.geekbrains.shop.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import ru.geekbrains.shop.persistence.entities.utils.PersistableEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Review extends PersistableEntity {

    private String commentary;

    @ManyToOne
    @JoinColumn(name = "shopuser")
    private Shopuser shopuser;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    private boolean approved;

}