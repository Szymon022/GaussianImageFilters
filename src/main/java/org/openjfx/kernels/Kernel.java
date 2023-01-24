package org.openjfx.kernels;

import java.util.Objects;

public abstract class Kernel {
    private final float[][] values;
    private final float divider;
    private final int offset;

    public Kernel(float[][] values, Float divider) {
        this.values = values;
        int rank = calculateRank();
        this.divider = Objects.requireNonNullElseGet(divider, this::calculateDivider);
        offset = rank / 2;
    }

    private int calculateRank() {
        var dimension = values.length;
        for (float[] row : values) {
            if (row.length != dimension) throw new IllegalArgumentException("Provided kernel is not a square matrix.");
        }
        return dimension;
    }

    private float calculateDivider() {
        var divider = 0.0f;
        for (float[] row : values) {
            for (float value : row) {
                divider += value;
            }
        }
        return divider;
    }

    public float getValue(int i, int j) {
        return values[i][j];
    }

    public float getDivider() {
        return divider;
    }

    public int getOffset() {
        return offset;
    }
}
