package org.openjfx.kernels;

public class EdgeDetectionKernel extends Kernel {
    public EdgeDetectionKernel() {
        super(new float[][]{
                {0, 1, 0},
                {1, -4, 1},
                {0, 1, 0}
        }, 1f);
    }
}
