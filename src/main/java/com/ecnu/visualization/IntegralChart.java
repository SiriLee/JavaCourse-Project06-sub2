package com.ecnu.visualization;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public final class IntegralChart {
    private static final double TRUE_VALUE = 2.0;
    private static final double MIN_POSITIVE = 1e-16;

    private IntegralChart() {
    }

    public static void showCharts(String csvPath) {
        List<DataPoint> points = readCsv(csvPath);
        if (points.isEmpty()) {
            throw new IllegalArgumentException("CSV has no data rows: " + csvPath);
        }

        SwingUtilities.invokeLater(() -> createAndShowUi(points));
    }

    private static void createAndShowUi(List<DataPoint> points) {
        JFreeChart approximationChart = createApproximationChart(points);
        JFreeChart errorLogLogChart = createErrorLogLogChart(points);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Approximations", new ChartPanel(approximationChart));
        tabs.addTab("Errors (Log-Log)", new ChartPanel(errorLogLogChart));

        JFrame frame = new JFrame("Numerical Integration Visualization");
        frame.setLayout(new BorderLayout());
        frame.add(tabs, BorderLayout.CENTER);
        frame.setSize(980, 680);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static JFreeChart createApproximationChart(List<DataPoint> points) {
        XYSeries trapezoidalSeries = new XYSeries("Trapezoidal");
        XYSeries simpsonSeries = new XYSeries("Simpson");

        for (DataPoint p : points) {
            trapezoidalSeries.add(p.n, p.trapezoidal);
            simpsonSeries.add(p.n, p.simpson);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(trapezoidalSeries);
        dataset.addSeries(simpsonSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Integral Approximations vs Number of Subintervals",
                "Number of subintervals n",
                "Integral approximation",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        ValueMarker trueValueMarker = new ValueMarker(TRUE_VALUE);
        trueValueMarker.setLabel("True value = 2.0");
        plot.addRangeMarker(trueValueMarker);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        plot.setRenderer(renderer);
        return chart;
    }

    private static JFreeChart createErrorLogLogChart(List<DataPoint> points) {
        XYSeries trapezoidalErrorSeries = new XYSeries("Trapezoidal Error");
        XYSeries simpsonErrorSeries = new XYSeries("Simpson Error");

        for (DataPoint p : points) {
            double errorT = Math.max(p.errorT, MIN_POSITIVE);
            double errorS = Math.max(p.errorS, MIN_POSITIVE);
            trapezoidalErrorSeries.add(p.n, errorT);
            simpsonErrorSeries.add(p.n, errorS);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(trapezoidalErrorSeries);
        dataset.addSeries(simpsonErrorSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Absolute Error (Log-Log)",
                "Number of subintervals n",
                "Absolute error",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        LogarithmicAxis xAxis = new LogarithmicAxis("Number of subintervals n (log10)");
        LogarithmicAxis yAxis = new LogarithmicAxis("Absolute error (log10)");
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        plot.setRenderer(renderer);
        return chart;
    }

    private static List<DataPoint> readCsv(String csvPath) {
        Path path = Path.of(csvPath);
        List<DataPoint> points = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line = reader.readLine();
            if (line == null) {
                return points;
            }

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] tokens = line.split(",");
                if (tokens.length < 5) {
                    continue;
                }

                int n = Integer.parseInt(tokens[0].trim());
                double trapezoidal = Double.parseDouble(tokens[1].trim());
                double errorT = Double.parseDouble(tokens[2].trim());
                double simpson = Double.parseDouble(tokens[3].trim());
                double errorS = Double.parseDouble(tokens[4].trim());
                points.add(new DataPoint(n, trapezoidal, errorT, simpson, errorS));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file: " + csvPath, e);
        }

        return points;
    }

    private static final class DataPoint {
        private final int n;
        private final double trapezoidal;
        private final double errorT;
        private final double simpson;
        private final double errorS;

        private DataPoint(int n, double trapezoidal, double errorT, double simpson, double errorS) {
            this.n = n;
            this.trapezoidal = trapezoidal;
            this.errorT = errorT;
            this.simpson = simpson;
            this.errorS = errorS;
        }
    }
}