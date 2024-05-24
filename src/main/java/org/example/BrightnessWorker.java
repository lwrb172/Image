package org.example;

import java.awt.image.BufferedImage;

public class BrightnessWorker implements Runnable {
    public int start;
    public int end;
    public BufferedImage image;
    public int value;

    public BrightnessWorker(int start, int end, BufferedImage image, int value) {
        this.start = start;
        this.end = end;
        this.image = image;
        this.value = value;
    }

    @Override
    public void run() {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = start; y < end; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int blue = rgb & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                blue = Clamp.clamp(blue + value, 0, 255);
                green = Clamp.clamp(green + value, 0, 255);
                red = Clamp.clamp(red + value, 0, 255);
                int newRgb = (0xFF << 24) | (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, newRgb);
            }
        }
    }

    public void increaseBrightnessMulti(int factor) throws InterruptedException {
        int height = image.getHeight();
        int width = image.getWidth();
        int cores = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[cores];
        int chunkSize = height / cores;

        for (int i = 0; i < cores; i++) {
            int start = i * chunkSize;
            int end = (i == cores - 1) ? height : (i + 1) * chunkSize;

            threads[i] = new Thread(new BrightnessWorker(start, end, image, factor));
            threads[i].start();
        }

        for (Thread thread : threads) thread.join();
    }

}
