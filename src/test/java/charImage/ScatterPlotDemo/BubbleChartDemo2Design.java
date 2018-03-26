/* ---------------------
 * BubbleChartDemo2.java
 * ---------------------
 * (C) Copyright 2005-2009, by Object Refinery Limited.
 */

package charImage.ScatterPlotDemo;

import extend.ExtendedScatterItemLabelGeneratory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYZToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

import javax.swing.*;
import java.awt.*;

/**
 * A bubble chart demo with item labels.
 */
public class BubbleChartDemo2Design extends ApplicationFrame {

    /**
     * A demonstration application showing a bubble chart.
     *
     * @param title  the frame title.
     */
    public BubbleChartDemo2Design(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(XYZDataset dataset) {
        JFreeChart chart = ChartFactory.createBubbleChart("Bubble Chart Demo 2",
                "X", "Y", dataset, PlotOrientation.VERTICAL, true, true,
                false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRenderer(new XYBubbleRenderer(0));
        plot.setForegroundAlpha(0.65f);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        String arr[] = {"t1","t2","t3","t4","t5","t6","t7","t8","t9","t10"};
        renderer.setBaseItemLabelGenerator(new ExtendedScatterItemLabelGeneratory(arr));
        renderer.setBaseToolTipGenerator(new StandardXYZToolTipGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER));

        // increase the margins to account for the fact that the auto-range
        // doesn't take into account the bubble size...
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setRange(0.0, 10.0);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, 10.0);
        return chart;
    }
    static double radius = 0.5;
    public static XYZDataset createDataset() {
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        double[] x = {2.1, 2.3, 2.3, 2.2, 2.2, 1.8, 1.8, 1.9, 2.3, 3.8};
        double[] y = {14.1, 11.1, 10.0, 8.8, 8.7, 8.4, 5.4, 4.1, 4.1, 25};
        double[] z = {radius, radius, radius, radius, radius, radius, radius, radius, radius, radius};
        double[][] series = new double[][] { x, y, z };
        dataset.addSeries("Series 1", series);

        return dataset;
    }
    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        BubbleChartDemo2Design demo = new BubbleChartDemo2Design(
                "JFreeChart: BubbleChartDemo2.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}