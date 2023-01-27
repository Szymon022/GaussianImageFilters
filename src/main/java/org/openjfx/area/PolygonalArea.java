package org.openjfx.area;

import javafx.geometry.Point2D;
import org.openjfx.geometry.Edge;
import org.openjfx.geometry.EdgeTable;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class PolygonalArea extends Area {

    private final List<Point2D> vertices;

    public PolygonalArea(List<Point2D> vertices) {
        this.vertices = vertices;
    }

    @Override
    public void forEach(Consumer<Point2D> action) {
        var edgeTable = EdgeTable.fromVertices(vertices);
        var activeEdgeTable = new LinkedList<Edge>();
        var scanline = edgeTable.getEntry(0).getY();

        while (edgeTable.getSize() > 0 || activeEdgeTable.size() > 0) {
            activeEdgeTable.addAll(edgeTable.popEdgesAtY(scanline));
            activeEdgeTable.sort(Comparator.comparingDouble(Edge::getXMin));

            for (int i = 0; i < activeEdgeTable.size() - 1; i += 2) {
                var startX = (int) activeEdgeTable.get(i).getXMin();
                var endX = (int) activeEdgeTable.get(i + 1).getXMin();
                for (int x = startX; x < endX; x++) {
                    action.accept(new Point2D(x, scanline));
                }
            }

            int y = scanline;
            activeEdgeTable.removeIf((edge) -> y >= edge.getYMax());
            scanline++;
            activeEdgeTable.forEach(Edge::incrementXMinByA);
        }

    }
}