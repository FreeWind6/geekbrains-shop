package ru.geekbrains.shop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import ru.geekbrains.shop.persistence.entities.Image;
import ru.geekbrains.shop.persistence.repositories.ImageRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    private String getImageForSpecificProduct(UUID id) {
        return imageRepository.obtainImageNameByProduct(id);
    }

    public BufferedImage loadFileAsResource(String id) throws IOException {
        try {
            String imageName = getImageForSpecificProduct(UUID.fromString(id));
            Resource resource = new ClassPathResource("/static/images/" + imageName);
            if (resource.exists()) {
                return ImageIO.read(resource.getFile());
            } else {
                log.error("Image not found!");
                throw new FileNotFoundException("File " + imageName + " not found!");
            }
        } catch (MalformedInputException | FileNotFoundException ex) {
            return null;
        }
    }

}