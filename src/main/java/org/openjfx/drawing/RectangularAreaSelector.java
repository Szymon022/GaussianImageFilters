package org.openjfx.drawing;

import org.openjfx.area.Area;
import org.openjfx.area.RectangularArea;

public class RectangularAreaSelector implements AreaSelector {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public RectangularAreaSelector(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public Area get() {
        return new RectangularArea(x, y, width, height);
    }

    @Override
    public void addPoint(int x, int y) {
    }

    @Override
    public void moveTo(int x, int y) {
    }
}
