package org.openjfx.kernels;

public class EmbossKernel extends Kernel {
    public EmbossKernel() {
        super(new float[][]{
                {-2, -1, 0},
                {-1, 1, 1},
                {0, 1, 2}
        }, null);
    }
}
