package com.ecnu.integration;

import com.ecnu.function.Linear;
import com.ecnu.function.Sin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewtonCotesTest {
    private final NewtonCotes newtonCatos = new NewtonCotes();

    @Test
    public void testTrapezoidalRule() {
        Linear function = new Linear(2.0, 3.0);

        assertEquals(10.0, newtonCatos.trapezoidal(function, 0.0, 2.0), 1e-10);
    }

    @Test
    public void testSimpsonRuleForSineIntegral() {
        Sin function = new Sin(1.0, 0.0);

        assertEquals(2.0 * Math.PI / 3.0, newtonCatos.simpson(function, 0.0, Math.PI), 1e-10);
    }
}