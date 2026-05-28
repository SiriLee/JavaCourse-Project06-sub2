package com.ecnu.integration;

import com.ecnu.Function;

public class NewtonCatos {
    public double Trapozoidal(Function f, double a, double b) {
        return (f.eval(a) + f.eval(b)) * (b - a) / 2.0;
    }

    public double Simpson(Function f, double a, double b) {
        double c = (a + b) / 2.0;
        return (f.eval(a) + 4.0 * f.eval(c) + f.eval(b)) * (b - a) / 6.0;
    }
}
