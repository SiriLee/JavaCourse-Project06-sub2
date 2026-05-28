package com.ecnu.function;

import com.ecnu.DifferentiableFunction;

public class Linear implements DifferentiableFunction {
    private final double k;
    private final double b;

    public Linear(double k, double b) {
        this.k = k;
        this.b = b;
    }

    @Override
    public double eval(double x) {
        return k * x + b;
    }

    @Override
    public double diff(double x) {
        return k;
    }
}
