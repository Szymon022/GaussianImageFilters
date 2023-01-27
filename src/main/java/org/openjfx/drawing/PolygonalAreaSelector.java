package org.openjfx.drawing;

import javafx.geometry.Point2D;
import org.openjfx.area.Area;
import org.openjfx.area.PolygonalArea;

import java.util.LinkedList;
import java.util.List;

public class PolygonalAreaSelector implements AreaSelector {

    private List<Point2D> vertices;

    public PolygonalAreaSelector() {
        vertices = new LinkedList<>();
    }

    @Override
    public Area get() {
        return new PolygonalArea(vertices);
    }

    @Override
    public void addPoint(int x, int y) {
        if (noSuchVertexExist(x, y)) {
            vertices.add(new Point2D(x, y));
        }
    }

    @Override
    public void moveBy(int x, int y) {
        vertices = vertices.stream()
                .map((point) -> {
                    int pointX = (int) point.getX();
                    int pointY = (int) point.getY();
                    return new Point2D(pointX + x, pointY + y);
                }).toList();
    }

    private boolean noSuchVertexExist(int x, int y) {
        return vertices.stream().noneMatch((point) -> (int) point.getX() == x && (int) point.getY() == y);
    }
}
