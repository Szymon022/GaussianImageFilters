package org.openjfx.area;

import javafx.geometry.Point2D;

import java.util.function.Consumer;

public class RectangularArea extends Area {

    private final int xStart;
    private final int yStart;
    private final int width;
    private final int height;

    public RectangularArea(int xStart, int yStart, int width, int height) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.width = width;
        this.height = height;
    }

    @Override
    public void forEach(Consumer<Point2D> action) {
        for (int x = xStart; x < xStart + width; x++) {
            for (int y = 0; y < yStart + height; y++) {
                action.accept(new Point2D(x, y));
            }
        }
    }

}
