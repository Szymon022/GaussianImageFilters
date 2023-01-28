package org.openjfx.area;

import javafx.geometry.Point2D;

import java.util.function.Consumer;

public class CircularArea extends Area {

    private final int xCenter;
    private final int yCenter;
    private final int radius;

    public CircularArea(int xCenter, int yCenter, int radius) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;
    }

    @Override
    public void forEach(Consumer<Point2D> action) {
        var minX = xCenter - radius;
        var minY = yCenter - radius;
        var maxX = xCenter + radius;
        var maxY = yCenter + radius;
        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                if (isPointInsideCircle(x, y)) action.accept(new Point2D(x, y));
            }
        }
    }

    private boolean isPointInsideCircle(int x, int y) {
        return squared(x - xCenter) + squared(y - yCenter) <= squared(radius);
    }

    private int squared(int number) {
        return number * number;
    }

    public int getXCenter() {
        return xCenter;
    }

    public int getYCenter() {
        return yCenter;
    }

    public int getRadius() {
        return radius;
    }
}
