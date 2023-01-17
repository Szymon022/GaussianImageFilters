package org.openjfx.kernels;

public class GaussianBlur3x3Kernel extends Kernel {
    public GaussianBlur3x3Kernel() {
        super(new float[][]{
                {1, 2, 1},
                {2, 4, 2},
                {1, 2, 1}
        }, null);
    }
}
