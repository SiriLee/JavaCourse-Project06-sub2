package com.ecnu.root;

import com.ecnu.DifferentiableFunction;
import com.ecnu.function.Linear;
import com.ecnu.function.NormalPDF;
import com.ecnu.function.Quadratic;
import com.ecnu.function.Sin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NewtonRootTest {
    private final NewtonRoot newtonRoot = new NewtonRoot();

    @Test
    public void testLinearRoot() {
        DifferentiableFunction function = new Linear(2.0, -4.0);

        assertEquals(2.0, newtonRoot.findRoot(function), 1e-10);
    }

    @Test
    public void testQuadraticRoot() {
        DifferentiableFunction function = new Quadratic(1.0, 0.0, -4.0);

        assertEquals(2.0, newtonRoot.findRoot(function, 3.0), 1e-10);
    }

    @Test
    public void testSinRoot() {
        DifferentiableFunction function = new Sin(1.0, 0.0);

        assertEquals(Math.PI, newtonRoot.findRoot(function, 3.0), 1e-10);
    }

    @Test
    public void testNormalPdfNoRoot() {
        DifferentiableFunction function = new NormalPDF(0.0, 2.0);

        assertThrows(RuntimeException.class, () -> newtonRoot.findRoot(function));
    }
}