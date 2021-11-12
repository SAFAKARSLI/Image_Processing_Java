package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String []args) throws IOException {

//        BufferedImage image = ImageIO.read(
//                new File("src/resources/images/jakeTheDogReading.png"));
//
////        Graphics graphs = image.createGraphics();
////
//        GUI gui = new GUI();
//        System.out.println(image);

        File f = new File(System.getProperty("user.home") + "\\Desktop\\asdf.txt");
        System.out.println(f.getName());
    }

}
