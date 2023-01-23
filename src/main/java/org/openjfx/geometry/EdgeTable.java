package org.openjfx.geometry;

import javafx.geometry.Point2D;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class EdgeTable {

    private final List<Entry> entries;

    public Entry getEntry(int index) {
        return entries.get(index);
    }

    public static EdgeTable fromVertices(List<Point2D> vertices) {
        var edgeTable = new EdgeTable(new LinkedList<>());
        var size = vertices.size();
        for (int i = 0; i < size; i++) {
            var start = vertices.get(i);
            var end = vertices.get((i + 1) % size);
            double startX = start.getX();
            double startY = start.getY();
            double endX = end.getX();
            double endY = end.getY();
            double yMax;
            double xMin;
            if (startY > endY) {
                yMax = startY;
                xMin = endX;
            } else {
                yMax = endY;
                xMin = startX;
            }
            double yMin = Math.min(startY, endY);
            double A = (endX - startX) / (endY - startY);
            var edge = new Edge(yMax, xMin, A);
            edgeTable.appendOrAddEdgeAtY((int) yMin, edge);
        }
        edgeTable.sort();
        return edgeTable;
    }

    public List<Edge> popEdgesAtY(int y) {
        Entry entry = null;
        for (var tableEntry : entries) {
            if (tableEntry.y == y) {
                entry = tableEntry;
                break;
            }
        }
        if (entry != null) {
            entries.remove(entry);
            return entry.edges;
        }
        return new LinkedList<>();
    }

    public void appendOrAddEdgeAtY(int y, Edge edge) {
        boolean found = false;
        Entry entry = null;
        for (var tableEntry : entries) {
            if (tableEntry.y == y) {
                found = true;
                entry = tableEntry;
                break;
            }
        }
        if (found) entry.edges.add(edge);
        else {
            var edges = new LinkedList<Edge>();
            edges.add(edge);
            entries.add(new Entry(y, edges));
        }
    }

    public int getSize() {
        return entries.size();
    }

    private EdgeTable(List<Entry> entries) {
        this.entries = entries;
    }

    private void sort() {
        entries.sort(Comparator.comparingInt(entry -> entry.y));
    }


    public class Entry {

        private final int y;
        private final List<Edge> edges;

        public Entry(int y, List<Edge> edges) {
            this.y = y;
            this.edges = edges;
        }

        public int getY() {
            return y;
        }
    }
}
