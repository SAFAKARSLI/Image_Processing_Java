package com.company;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MakingPolylines extends JFrame implements MouseListener, MouseMotionListener {

    int x, y;

    List<Point> nodes = new LinkedList<>();
    Rectangle selectedArea;
    Polygon selectedAreaInShape;
    BufferedImage img;
    BufferedImage output;

    public static void main(String args[]) throws IOException {

        new MakingPolylines();

    }

    public MakingPolylines() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Provide the absolute path of the image that will be processed:");
        try {
            this.img = ImageIO.read( new File(scan.nextLine()));
        } catch (IOException e ) {
            e.printStackTrace();
        }


        setLayout(null);
        setSize(800,600);

//        // Get scaled form of the input image in order to apply the algorithm properly
//        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
//        Image scaledImg = img.getScaledInstance(screenWidth,screenHeight, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(img);

        JButton button = new JButton("Done");
        button.setBounds(getWidth()/2,getHeight()-50, 100, 30);
        button.addActionListener(e -> {

            Graphics g = getGraphics();
            // Complete the shape
            Point lastNode = nodes.get(nodes.size()-1);
            Point firstNode = nodes.get(0);
            g.drawLine(
                    lastNode.x, lastNode.y,
                    firstNode.x, firstNode.y
            );


            this.output = createCopyOfInputImage(this.img);
            getSmallestPossibleRect(this.nodes);
            makePolygon(nodes);
            processImage(this.img, selectedArea);


        });

        button.setLayout(null);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        lbl.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

        add(lbl);
        add(button);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Fullscreen initially
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        addMouseListener(this);
        addMouseMotionListener(this);

    }

    private void makePolygon(List<Point> nodes) {
        int[] xPoints = new int[nodes.size()];
        int[] yPoints = new int[nodes.size()];

        for(int i = 0; i<nodes.size(); i++) {
            xPoints[i] = nodes.get(i).x - 7;
            yPoints[i] = nodes.get(i).y - 30;
        }

        this.selectedAreaInShape = new Polygon(xPoints, yPoints, nodes.size());
    }

    private BufferedImage createCopyOfInputImage(BufferedImage inputImage){
        try {
            File f = new File(System.getProperty("user.home") + "\\Desktop\\copyOfImage.jpg");
            f.createNewFile();
            BufferedImage copyOfImage = ImageIO.read(f);
            Graphics g = inputImage.createGraphics();
            g.drawImage(copyOfImage,0,0,null);
            return copyOfImage;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    private void processImage(BufferedImage img, Rectangle rect) {

        // Image Processing
        int width = img.getWidth();
        int height = img.getHeight();

        // convert to red image
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {

                if(selectedAreaInShape.contains(x,y)) {
                    int p = img.getRGB(x,y);

                    int a = (p>>24)&0xff;
                    int r = (p>>16)&0xff;

                    // set new RGB
                    // keeping the r value same as in original
                    // image and setting g and b as 0.
                    p = (a<<24) | (r<<16) | (0<<8) | 0;

                    img.setRGB(x, y, p);
                }

            }
        }

        // write image
        try
        {
            File f = new File(System.getProperty("user.home") + "\\Desktop\\copyOfImage.jpg");
            ImageIO.write(img, "jpg", f);
        }
        catch(IOException e) {
            System.out.println(e);
        }

    }

    private void getSmallestPossibleRect(List<Point> nodes) {

        Point top = new Point(0, Integer.MAX_VALUE);
        Point bottom = new Point(0, Integer.MIN_VALUE);
        Point left = new Point(Integer.MAX_VALUE, 0);
        Point right = new Point(Integer.MIN_VALUE, 0);


        for(Point node: nodes) {

            if(node.y < top.y) {
                top = node; // Top Node of the Rect
            } if (node.y > bottom.y) {
                bottom = node; // Bottom Node of the Rect
            } if (node.x > right.x) {
                right = node; // Rigth Node of the Rect
            } if (node.x < left.x) {
                left = node; // Left Node of the Rect
            }

        }

        Point startNode = new Point(left.x, top.y);
        Dimension rect = new Dimension(Math.abs(left.x - right.x), Math.abs(top.y - bottom.y));

        Graphics g = getGraphics();
        g.setColor(Color.red);
        g.drawRect(startNode.x, startNode.y, rect.width, rect.height);

        // Because top left corner Point[x:7, y:30];
        selectedArea = new Rectangle(startNode.x - 7, startNode.y-30, rect.width, rect.height);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        nodes.add( new Point(x, y));

        Graphics g = getGraphics();
        g.fillOval(x-3,y-3,6,6);

        if(nodes.size() > 1) {
            Point previousNode = nodes.get(nodes.size() -2);
            g.setColor(Color.black);
            g.drawLine(previousNode.x, previousNode.y, x, y);
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
