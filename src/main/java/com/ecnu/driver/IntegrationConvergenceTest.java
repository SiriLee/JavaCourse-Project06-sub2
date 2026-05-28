package com.ecnu.driver;

import com.ecnu.Function;
import com.ecnu.function.Sin;
import com.ecnu.integration.NewtonCotes;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

public class IntegrationConvergenceTest {
    public static void main(String[] args) {
        Function sinFunc = new Sin(1.0, 0.0);  // sin(x)
        double a = 0, b = Math.PI;
        double exact = 2.0;

        NewtonCotes integrator = new NewtonCotes();
        Path output = Path.of("data", "sin_integral.csv");

        try {
            Files.createDirectories(output.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(
                    output,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE
            )) {
                writer.write("n,Trapezoidal,ErrorT,Simpson,ErrorS");
                writer.newLine();

                for (int n = 10; n <= 1000; n += 10) {  // 步长10
                    double trap = integrator.trapezoidal(sinFunc, a, b, n);
                    double simp = integrator.simpson(sinFunc, a, b, n);
                    double errT = Math.abs(trap - exact);
                    double errS = Math.abs(simp - exact);
                    writer.write(String.format(Locale.US, "%d,%.12f,%.12f,%.12f,%.12f", n, trap, errT, simp, errS));
                    writer.newLine();
                }
            }
            System.out.println("Data written to " + output.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write integration data.", e);
        }
    }
}
