package com.company;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;

public class BlankArea extends JLabel {
    Dimension minSize = new Dimension(1000, 3000);

    public BlankArea(Color color) {
        setBackground(color);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
}