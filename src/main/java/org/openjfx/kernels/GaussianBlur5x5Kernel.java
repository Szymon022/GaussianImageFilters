package org.openjfx.kernels;

public class GaussianBlur5x5Kernel extends Kernel {
    public GaussianBlur5x5Kernel() {
        super(new float[][]{
                {1, 4, 6, 4, 1},
                {4, 16, 24, 16, 4},
                {6, 24, 36, 24, 6},
                {4, 16, 24, 16, 4},
                {1, 4, 6, 4, 1}
        }, null);
    }
}
