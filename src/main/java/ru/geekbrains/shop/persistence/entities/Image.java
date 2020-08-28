package ru.geekbrains.shop.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.shop.persistence.entities.utils.PersistableEntity;

import javax.persistence.Entity;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Image extends PersistableEntity {
    private String name;
}