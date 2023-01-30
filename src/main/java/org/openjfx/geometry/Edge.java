package org.openjfx.geometry;

public class Edge {

    private double yMax;
    private double xMin;
    private double A;

    public Edge(double yMax, double xMin, double a) {
        this.yMax = yMax;
        this.xMin = xMin;
        A = a;
    }

    public void incrementXMinByA() {
        xMin += A;
    }

    public double getYMax() {
        return yMax;
    }

    public double getXMin() {
        return xMin;
    }
}
