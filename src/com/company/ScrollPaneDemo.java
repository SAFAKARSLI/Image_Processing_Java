package com.company;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ScrollPaneDemo extends JFrame implements MouseListener {
    public ScrollPaneDemo() throws IOException {
        super("JScrollPane Demo");
        Scanner scan = new Scanner(System.in);
        ImageIcon ii = new ImageIcon(ImageIO.read( new File(scan.next())));
        scan.close();
        JLabel lbl = new JLabel(ii);
        JScrollPane jsp = new JScrollPane(lbl);
        add(jsp);

        addMouseListener(this);
        setSize(300, 250);
        setVisible(true);

    }

    public static void main(String[] args) throws IOException {
        new ScrollPaneDemo();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(MouseInfo.getPointerInfo().getLocation().toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(MouseInfo.getPointerInfo().getLocation().toString());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
