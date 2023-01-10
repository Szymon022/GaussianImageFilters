package org.szymon.components;

import javax.swing.*;
import java.awt.*;

public class ImageCanvas extends JPanel {
    private final int width;
    private final int height;

    public ImageCanvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
