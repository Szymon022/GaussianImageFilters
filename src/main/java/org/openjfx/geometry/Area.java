package org.openjfx.geometry;

import java.awt.geom.Point2D;
import java.util.function.Consumer;

public abstract class Area {

    public void forEach(Consumer<Point2D> block) {
        // iterate over each point in area and pass it to block
    }
}
