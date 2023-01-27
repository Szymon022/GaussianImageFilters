package org.openjfx.drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.openjfx.area.Area;
import org.openjfx.area.CircularArea;
import org.openjfx.area.PolygonalArea;
import org.openjfx.area.RectangularArea;

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
        System.out.println("Area ("+ xS + "," + yS + "), radius=" + radius);
        graphicsContext.strokeOval(
                xS - radius,
                yS - radius,
                2 * radius,
                2 * radius
        );
    }

    private void paintPolygonalArea(Area area) {
        PolygonalArea polygonalArea = (PolygonalArea) area;
    }

    private double getCanvasHeight() {
        return graphicsContext.getCanvas().getHeight();
    }

    private double getCanvasWidth() {
        return graphicsContext.getCanvas().getWidth();
    }
}
