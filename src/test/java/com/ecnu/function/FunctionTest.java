package com.ecnu.function;

import com.ecnu.Function;
import com.ecnu.DifferentiableFunction;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionTest {
    @Test
    void testMain() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        String expectedOutput = String.join(System.lineSeparator(),
                "f(1) = 3.0",
                "f'(1) = 2.0",
                "",
                "f(1) = 0.0",
                "f'(1) = -1.0",
                "",
                "f(1) = 0.8414709848078965",
                "f'(1) = 0.5403023058681398",
                "",
                "f(1) = 0.6065306597126334",
                "f'(1) = -0.6065306597126334");

        assertEquals(expectedOutput, outputStream.toString().stripTrailing());
    }

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
