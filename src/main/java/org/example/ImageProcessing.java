package org.example;

import javax.imageio.ImageIO;
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
}
