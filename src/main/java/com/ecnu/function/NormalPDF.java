package com.ecnu.function;

import com.ecnu.DifferentiableFunction;

public class NormalPDF implements DifferentiableFunction {
    private final double mu;
    private final double sigma;

    public NormalPDF(double mu, double sigma) {
        this.mu = mu;
        this.sigma = sigma;
    }

    @Override
    public double eval(double x) {
        return Math.exp( - (x - mu) * (x - mu) / (2 * sigma * sigma) );
    }

    @Override
    public double diff(double x) {
        return - (x - mu) / (sigma * sigma) * eval(x);
    }
}
