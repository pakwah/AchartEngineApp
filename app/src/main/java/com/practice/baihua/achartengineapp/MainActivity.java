package com.practice.baihua.achartengineapp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


public class MainActivity extends Activity {

    private GraphicalView mChart;

    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

    private XYMultipleSeriesRenderer mRenderer;

    private XYSeries mCurrentSeries;

    private XYSeriesRenderer mCurrentRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRenderer = getRenderer();
        mCurrentRenderer = getXYSeriesRenderer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayout layout = (LinearLayout) findViewById(R.id.container);
        if(mChart == null) {
            initChart();
            addSampleData();
            mChart = ChartFactory.getBarChartView(this, mDataset, mRenderer, BarChart.Type.DEFAULT);
            layout.addView(mChart);
        } else {
            mChart.repaint();
        }
    }

    private void initChart() {
        mCurrentSeries = new XYSeries("Points");
        mDataset.addSeries(mCurrentSeries);
        mRenderer.addSeriesRenderer(mCurrentRenderer);
    }

    private void addSampleData() {
        mCurrentSeries.add(1, 3);
        mCurrentSeries.add(2, 2);
        mCurrentSeries.add(3, 4);
        mCurrentSeries.add(4, 6);
        mCurrentSeries.add(5, 2);
        mCurrentSeries.add(6, 5);
        mCurrentSeries.add(7, 3);
        mCurrentSeries.add(8, 9);
    }

    private XYMultipleSeriesRenderer getRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(20);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(18);
        renderer.setMargins(new int[]{30, 40, 10, 10});
        renderer.setAxesColor(Color.BLACK);
        renderer.setLabelsColor(Color.DKGRAY);
        renderer.setMarginsColor(Color.parseColor("#00ffffff"));
        renderer.setDisplayChartValues(true);

        setChartSettings(renderer);
        return renderer;
    }

    private XYSeriesRenderer getXYSeriesRenderer() {
        XYSeriesRenderer seriesRenderer = new XYSeriesRenderer();
        seriesRenderer.setColor(Color.parseColor("#01A9DB"));
        seriesRenderer.setDisplayChartValues(true);
        seriesRenderer.setChartValuesTextSize(15);
        seriesRenderer.setChartValuesTextAlign(Paint.Align.RIGHT);
        return seriesRenderer;
    }

    private void setChartSettings(XYMultipleSeriesRenderer renderer) {
        renderer.setChartTitle("Statistics");
        renderer.setXTitle("Day");
        renderer.setYTitle("Points");

        renderer.setXLabels(0);
        renderer.setXAxisMin(0.5);
        renderer.setXAxisMax(8.5);
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(10);
        renderer.setYLabelsAlign(Paint.Align.RIGHT);

        renderer.setXLabelsColor(Color.DKGRAY);
        renderer.setYLabelsColor(0, Color.DKGRAY);

        renderer.setBarSpacing(0.5);
        renderer.addXTextLabel(1, "M");
        renderer.addXTextLabel(2, "T");
        renderer.addXTextLabel(3, "W");
        renderer.addXTextLabel(4, "Th");
        renderer.addXTextLabel(5, "F");
        renderer.addXTextLabel(6, "S");
        renderer.addXTextLabel(7, "Sun");
        renderer.addXTextLabel(8, "Bonus");
    }
}
