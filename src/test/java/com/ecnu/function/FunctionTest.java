package com.ecnu.function;

import com.ecnu.Function;
import com.ecnu.DifferentiableFunction;
import com.ecnu.function.*;

public class FunctionTest {
    public static void main(String[] args) {
        Function[] functions = new Function[4];
        functions[0] = new Linear(2, 1); // 2x + 1
        functions[1] = new Quadratic(1, -3, 2); // x^2 - 3x + 2
        functions[2] = new Sin(1, 0); // sin(x)
        functions[3] = new NormalPDF(0, 1); // N(0, 1)
        for (Function f : functions) {
            System.out.println("f(1) = " + f.eval(1));
            if (f instanceof DifferentiableFunction) {
                DifferentiableFunction df = (DifferentiableFunction) f;
                System.out.println("f'(1) = " + df.diff(1));
            }
            System.out.println();
        }
    }
}
