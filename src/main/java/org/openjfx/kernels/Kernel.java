package org.openjfx.kernels;

import java.util.Objects;

public abstract class Kernel {
    private final float[][] values;
    private final float divider;
    private final int rank;
    private final int offset;

    public Kernel(float[][] values, Float divider) {
        this.values = values;
        rank = calculateRank();
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
        var sum = 0.0f;
        for (float[] row : values) {
            for (float value : row) {
                sum += value;
            }
        }
        return sum / (rank * rank);
    }

    public float getValue(int i, int j) {
        return values[i][j];
    }

    public float[][] getValues() {
        return values;
    }

    public float getDivider() {
        return divider;
    }

    public int getRank() {
        return rank;
    }

    public int getOffset() {
        return offset;
    }
}
