package org.openjfx.kernels;

public class IdentityKernel extends Kernel {
    public IdentityKernel() {
        super(new float[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        }, null);
    }
}
