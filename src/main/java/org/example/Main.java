package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ImageProcessing imageProcessing = new ImageProcessing();
        try {
            imageProcessing.read("thailand1.jpg");
            imageProcessing.write("thailand2.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}