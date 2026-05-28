package com.ecnu.function;

import com.ecnu.Function;
import com.ecnu.DifferentiableFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionTest {
    @Test
    public void testLinear() {
        DifferentiableFunction function = new Linear(2.0, 3.0);

        assertEquals(13.0, function.eval(5.0), 1e-10);
        assertEquals(2.0, function.diff(5.0), 1e-10);
    }

    @Test
    public void testQuadratic() {
        DifferentiableFunction function = new Quadratic(2.0, 3.0, 4.0);

        assertEquals(18.0, function.eval(2.0), 1e-10);
        assertEquals(11.0, function.diff(2.0), 1e-10);
    }

    @Test
    public void testSin() {
        DifferentiableFunction function = new Sin(1.0, 0.0);

        assertEquals(1.0, function.eval(Math.PI / 2), 1e-10);
        assertEquals(0.0, function.diff(Math.PI / 2), 1e-10);
    }

    @Test
    public void testNormalPDF() {
        DifferentiableFunction function = new NormalPDF(0.0, 2.0);

        assertEquals(1.0, function.eval(0.0), 1e-10);
        assertEquals(0.0, function.diff(0.0), 1e-10);
        assertEquals(Math.exp(-0.5), function.eval(2.0), 1e-10);
        assertEquals(-0.5 * Math.exp(-0.5), function.diff(2.0), 1e-10);
    }

    @Test
    public void testFunctionPolymorphism() {
        Function function = new Linear(-1.5, 4.0);

        assertEquals(1.0, function.eval(2.0), 1e-10);
    }
}
