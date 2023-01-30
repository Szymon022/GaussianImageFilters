package org.openjfx.drawing;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.openjfx.area.Area;
import org.openjfx.area.CircularArea;
import org.openjfx.area.PolygonalArea;
import org.openjfx.area.RectangularArea;

import java.util.List;

public class AreaPainter {

    private final GraphicsContext graphicsContext;

    public AreaPainter(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void paint(Area area) {
        if (area instanceof RectangularArea) {
            paintRectangularArea(area);
        } else if (area instanceof CircularArea) {
            paintCircularArea(area);
        } else if (area instanceof PolygonalArea) {
            paintPolygonalArea(area);
        }
    }

    private void paintRectangularArea(Area area) {
        RectangularArea rectangularArea = (RectangularArea) area;
        graphicsContext.clearRect(0, 0, getCanvasWidth(), getCanvasHeight());
        graphicsContext.setStroke(Color.DEEPPINK);
        graphicsContext.strokeRect(
                rectangularArea.getXStart(),
                rectangularArea.getYStart(),
                rectangularArea.getWidth(),
                rectangularArea.getHeight()
        );
    }

    private void paintCircularArea(Area area) {
        CircularArea circularArea = (CircularArea) area;
        graphicsContext.clearRect(0, 0, getCanvasWidth(), getCanvasHeight());
        graphicsContext.setStroke(Color.DEEPPINK);
        var xS = circularArea.getXCenter();
        var yS = circularArea.getYCenter();
        var radius = circularArea.getRadius();
        graphicsContext.strokeOval(
                xS - radius,
                yS - radius,
                2 * radius,
                2 * radius
        );
    }

    private void paintPolygonalArea(Area area) {
        PolygonalArea polygonalArea = (PolygonalArea) area;
        if (polygonalArea.isClosed()) {
            drawClosedPolygon(polygonalArea.getVertices());
        } else drawIncompletePolygon(polygonalArea.getVertices(), true);
    }

    private void drawClosedPolygon(List<Point2D> vertices) {
        graphicsContext.clearRect(0, 0, getCanvasWidth(), getCanvasHeight());
        drawIncompletePolygon(vertices, false);
        graphicsContext.setStroke(Color.DEEPPINK);
        var firstVertex = vertices.get(0);
        var lastVertex = vertices.get(vertices.size() - 1);
        graphicsContext.strokeLine(
                firstVertex.getX(),
                firstVertex.getY(),
                lastVertex.getX(),
                lastVertex.getY()
        );
    }

    private void drawIncompletePolygon(List<Point2D> vertices, boolean clearCanvas) {
        if (clearCanvas) graphicsContext.clearRect(0, 0, getCanvasWidth(), getCanvasHeight());
        var size = vertices.size();
        if (size == 0) return;
        var previousPoint = vertices.get(0);
        drawPoint(previousPoint);
        for (int i = 1; i < size; i++) {
            var currentPoint = vertices.get(i);
            drawPoint(currentPoint);
            graphicsContext.setStroke(Color.DEEPPINK);
            graphicsContext.strokeLine(
                    previousPoint.getX(),
                    previousPoint.getY(),
                    currentPoint.getX(),
                    currentPoint.getY()
            );
            previousPoint = currentPoint;
        }
    }

    private void drawPoint(Point2D point) {
        graphicsContext.setStroke(Color.DEEPPINK);
        var x = point.getX();
        var y = point.getY();
        var radius = 5;
        graphicsContext.strokeOval(
                x - radius,
                y - radius,
                2 * radius,
                2 * radius
        );
    }

    private double getCanvasHeight() {
        return graphicsContext.getCanvas().getHeight();
    }

    private double getCanvasWidth() {
        return graphicsContext.getCanvas().getWidth();
    }
}
