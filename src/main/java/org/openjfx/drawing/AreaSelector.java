package org.openjfx.drawing;

import org.openjfx.area.Area;

public interface AreaSelector {

    Area get();

    void addPoint(int x, int y);

    void moveBy(int x, int y);
}
