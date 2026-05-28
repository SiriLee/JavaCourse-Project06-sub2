package com.ecnu.integration;

import com.ecnu.Function;

public class NewtonCotes {
    public double trapezoidal(Function f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.5 * (f.eval(a) + f.eval(b));
        for (int i = 1; i < n; i++) {
            sum += f.eval(a + i * h);
        }
        return sum * h;
    }

    public double simpson(Function f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = f.eval(a) + f.eval(b);
        for (int i = 1; i < n; i += 2) {
            sum += 4 * f.eval(a + i * h);
        }
        for (int i = 2; i < n; i += 2) {
            sum += 2 * f.eval(a + i * h);
        }
        return sum * h / 3.0;
    }
}
