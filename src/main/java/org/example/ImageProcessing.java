package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {
    public BufferedImage image;
    public void read(String path) throws IOException {
        image = ImageIO.read(new File(path));
    }
    public void write(String path) throws IOException {
        String format= "jpg";
        if(path.lastIndexOf('.')!=-1) {
            format = path.substring(path.lastIndexOf('.') + 1);
        }
        ImageIO.write(image,format,new File(path));
    }
    public void increaseBrightness(int value) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int blue = rgb & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                blue = Clamp.clamp(blue + value, 0, 255);
                green = Clamp.clamp(green + value, 0, 255);
                red = Clamp.clamp(red + value, 0, 255);
                int newRgb = (0xFF << 24) | (red << 16) | (green << 8) | blue;
//                System.out.println("RGB signed 32 bit = "+rgb+" hex: "+Integer.toHexString(rgb)
//                        + " binary: " + Integer.toBinaryString(rgb)
//                        + " blue: " + blue + " green: " + green + " red: " + red
//                        + " new Rgb: " + Integer.toBinaryString(newRgb));
                image.setRGB(x, y, newRgb);
            }
        }
    }
}
