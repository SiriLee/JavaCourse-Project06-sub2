package com.ecnu.root;

import com.ecnu.DifferentiableFunction;

public class NewtonRoot {
    private static final int MAX_ITER = 1000;
    private static final double EPSILON = 1e-10;

    public double findRoot(DifferentiableFunction f, double initialGuess) {
        double x = initialGuess; // Initial guess
        for (int i = 0; i < MAX_ITER; i++) {
            double fx = f.eval(x);
            double dfx = f.diff(x);

            if (Math.abs(fx) < EPSILON) {
                return x; // Found a root
            }

            if (Math.abs(dfx) < EPSILON) {
                throw new RuntimeException("Derivative is too small, no convergence.");
            }

            x = x - fx / dfx; // Newton's method update
        }
        throw new RuntimeException("Maximum iterations reached, no root found.");
    }

    public double findRoot(DifferentiableFunction f) {
        return findRoot(f, 0.0); // Default initial guess
    }
}
