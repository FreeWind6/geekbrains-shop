package ru.geekbrains.shop.persistence.entities.utils;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.util.UUID;

@Data
@MappedSuperclass
public abstract class PersistableEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    protected UUID id;

}