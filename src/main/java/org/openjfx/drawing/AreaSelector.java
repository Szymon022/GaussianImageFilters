package org.openjfx.drawing;

import org.openjfx.area.Area;

public interface AreaSelector {

    Area get();

    void addPoint(int x, int y);

    void moveTo(int x, int y);
}
