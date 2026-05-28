package com.ecnu.integration;

import com.ecnu.function.Linear;
import com.ecnu.function.Sin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewtonCotesTest {
    private final NewtonCatos newtonCatos = new NewtonCatos();

    @Test
    public void testTrapezoidalRule() {
        Linear function = new Linear(2.0, 3.0);

        assertEquals(10.0, newtonCatos.Trapozoidal(function, 0.0, 2.0), 1e-10);
    }

    @Test
    public void testSimpsonRuleForSineIntegral() {
        Sin function = new Sin(1.0, 0.0);

        assertEquals(2.0 * Math.PI / 3.0, newtonCatos.Simpson(function, 0.0, Math.PI), 1e-10);
    }
}