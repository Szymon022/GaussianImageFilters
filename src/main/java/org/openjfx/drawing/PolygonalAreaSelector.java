package org.openjfx.drawing;

import javafx.geometry.Point2D;
import org.openjfx.area.Area;
import org.openjfx.area.PolygonalArea;

import java.util.LinkedList;

public class PolygonalAreaSelector implements AreaSelector {

    private LinkedList<Point2D> vertices;
    private boolean isClosed;

    public PolygonalAreaSelector() {
        vertices = new LinkedList<>();
        isClosed = false;
    }

    @Override
    public Area get() {
        return new PolygonalArea(vertices, isClosed);
    }

    @Override
    public void addPoint(int x, int y) {
        if (isClosed) return;
        if (isOriginVertex(x, y) && vertices.size() > 2) {
            isClosed = true;
        } else if (noSuchVertexExist(x, y)) {
            vertices.add(new Point2D(x, y));
        }
    }

    @Override
    public void moveTo(int x, int y) {
        if (vertices.isEmpty()) return;
        var origin = vertices.get(0);
        var xShift = x - origin.getX();
        var yShift = y - origin.getY();
        vertices = shiftVerticesBy(vertices, xShift, yShift);
    }

    private boolean noSuchVertexExist(int x, int y) {
        return vertices.stream().noneMatch((point) -> (int) point.getX() == x && (int) point.getY() == y);
    }

    private boolean isOriginVertex(int x, int y) {
        if (vertices.size() == 0) return false;
        var threshold = 5;
        var originVertex = vertices.get(0);
        var originX = (int) originVertex.getX();
        var originY = (int) originVertex.getY();
        return Math.abs(x - originX) <= threshold && Math.abs(y - originY) <= threshold;
    }

    private LinkedList<Point2D> shiftVerticesBy(LinkedList<Point2D> vertices, double xShift, double yShift) {
        var shiftedVertices = new LinkedList<Point2D>();
        for (Point2D vertex : vertices) {
            shiftedVertices.add(new Point2D(vertex.getX() + xShift, vertex.getY() + yShift));
        }
        return shiftedVertices;
    }
}
