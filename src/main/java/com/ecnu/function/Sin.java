package com.ecnu.function;

import com.ecnu.DifferentiableFunction;

public class Sin implements DifferentiableFunction {
    private final double omega;
    private final double phi;

    public Sin(double omega, double phi) {
        this.omega = omega;
        this.phi = phi;
    }

    @Override
    public double eval(double x) {
        return Math.sin(omega * x + phi);
    }

    @Override
    public double diff(double x) {
        return omega * Math.cos(omega * x + phi);
    }
}
