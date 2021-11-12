package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

public class Main {

    public static void main(String []args) throws IOException {

//        BufferedImage image = ImageIO.read(
//                new File("src/resources/images/jakeTheDogReading.png"));
//
////        Graphics graphs = image.createGraphics();
////
//        GUI gui = new GUI();
//        System.out.println(image);

        int[] a = new int[2];

        System.out.println(a.length);
        a[0] = 12;
        try {
            a[3] = 15;
        } catch (ArrayIndexOutOfBoundsException e) {
            int[] b = new int[a.length*2];
            for(int i = 0; i< a.length; i++) {
                b[i] = a[i];
            }
            a = b;
        }

        System.out.println(a.length);
        System.out.println(a[2]);

    }

}
