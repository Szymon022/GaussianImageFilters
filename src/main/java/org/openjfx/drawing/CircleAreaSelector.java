package org.openjfx.drawing;

import org.openjfx.area.Area;
import org.openjfx.area.CircularArea;

public class CircleAreaSelector implements AreaSelector {

    private int xCenter;
    private int yCenter;
    private int radius;

    public CircleAreaSelector() {
        xCenter = 0;
        yCenter = 0;
        radius = 0;
    }

    @Override
    public Area get() {
        return new CircularArea(xCenter, yCenter, radius);
    }

    @Override
    public void addPoint(int x, int y) {
        xCenter = x;
        yCenter = y;
    }

    @Override
    public void moveBy(int x, int y) {
        xCenter = x;
        yCenter = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
