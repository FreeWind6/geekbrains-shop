package ru.geekbrains.shop.utils;

import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.util.stream.IntStream;

@Component
public class CaptchaGenerator {

    private String captchaString;

    private Color backgroundColor = Color.white;
    private Color borderColor = Color.black;
    private Color textColor = Color.black;
    private Color circleColor = new Color(190, 160, 150);

    private Font textFont = new Font("Verdana", Font.BOLD, 24);

    public BufferedImage getCaptchaImage() {

        try {

            int width = 200;
            int height = 35;
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
            graphics2D.setColor(backgroundColor);
            graphics2D.fillRect(0, 0, width, height);
            graphics2D.setColor(circleColor);

            int circlesToDraw = 25;
            IntStream.range(0, circlesToDraw).map(i -> (int) (Math.random() * height / 2.0)).forEach(L -> {
                int X = (int) (Math.random() * width - L);
                int Y = (int) (Math.random() * height - L);
                graphics2D.draw3DRect(X, Y, L * 2, L * 2, true);
            });

            graphics2D.setColor(textColor);
            graphics2D.setFont(textFont);

            FontMetrics fontMetrics = graphics2D.getFontMetrics();

            int maxAdvance = fontMetrics.getMaxAdvance();
            int fontHeight = fontMetrics.getHeight();

            String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXYabcdefghjkmnpqrstuvwxy23456789";
            char[] chars = elegibleChars.toCharArray();
            float horizMargin = 12.0f;
            float spaceForLetters = -horizMargin * 2 + width;
            int charsToPrint = 6;
            float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);
            StringBuilder finalString = new StringBuilder();
            IntStream.range(0, charsToPrint).forEach(i -> {
                double randomValue = Math.random();
                int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
                char characterToShow = chars[randomIndex];
                finalString.append(characterToShow);
                int charWidth = fontMetrics.charWidth(characterToShow);
                int charDim = Math.max(maxAdvance, fontHeight);
                int halfCharDim = charDim / 2;
                BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
                Graphics2D charGraphics = charImage.createGraphics();
                charGraphics.translate(halfCharDim, halfCharDim);
                double rotationRange = 0.8;
                double angle = (Math.random() - 0.5) * rotationRange;
                charGraphics.transform(AffineTransform.getRotateInstance(angle));
                charGraphics.translate(-halfCharDim, -halfCharDim);
                charGraphics.setColor(textColor);
                charGraphics.setFont(textFont);
                int charX = (int) (0.5 * charDim - 0.5 * charWidth);
                charGraphics.drawString("" + characterToShow, charX, (int) ((charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent()));
                float x = horizMargin + spacePerChar * (i) - charDim / 2.0f;
                int y = (height - charDim) / 2;
                graphics2D.drawImage(charImage, (int) x, y, charDim, charDim, null, null);
                charGraphics.dispose();
            });
            graphics2D.setColor(borderColor);
            graphics2D.drawRect(0, 0, width - 1, height - 1);
            graphics2D.dispose();
            captchaString = finalString.toString();
            return bufferedImage;
        } catch (Exception ioe) {
            throw new RuntimeException("Unable to build image", ioe);
        }

    }

    public String getCaptchaString() {
        return captchaString;
    }

}