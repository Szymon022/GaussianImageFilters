package org.openjfx.kernels;

public class Sharpen3x3Kernel extends Kernel {
    public Sharpen3x3Kernel() {
        super(new float[][]{
                {0, -1, 0},
                {-1, 5, -1},
                {0, -1, 0}
        }, null);
    }
}
