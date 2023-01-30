package org.openjfx.area;

import javafx.geometry.Point2D;

import java.util.function.Consumer;

public interface IterableArea {

    void forEach(Consumer<Point2D> action);
}
