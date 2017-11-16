package com.imdglobalservices.psi.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.imdglobalservices.psi.R;
import com.imdglobalservices.psi.database.PSIDatabaseImplementation;
import com.imdglobalservices.psi.models.DataIndex;
import com.imdglobalservices.psi.models.PSIDate;
import com.imdglobalservices.psi.utils.ContentHelper;

import java.util.ArrayList;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.16.11
 */
public class PSIStatisticFragment extends Fragment implements OnChartValueSelectedListener {
    private LineChart lineChart;

    private ArrayList<String> arrayTime = new ArrayList<>();
    private ArrayList<Entry> arrayCentral = new ArrayList<>();
    private ArrayList<Entry> arrayNorth = new ArrayList<>();
    private ArrayList<Entry> arrayWest = new ArrayList<>();
    private ArrayList<Entry> arraySouth = new ArrayList<>();
    private ArrayList<Entry> arrayEast = new ArrayList<>();
    private ArrayList<Entry> arrayNational = new ArrayList<>();
    private ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    private PSIDate psiByDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic_psi, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLineChart();
        setData();
    }

    /**
     * this method for initialize all view
     *
     * @param view root for view
     */
    private void initView(View view) {
        lineChart = view.findViewById(R.id.line_chart_statistic);
    }

    /**
     * this method for initialize data
     */
    private void initLineChart() {

        lineChart.setOnChartValueSelectedListener(this);
        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawBorders(true);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setDrawAxisLine(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getXAxis().setDrawAxisLine(false);
        lineChart.getXAxis().setDrawGridLines(false);

        // no description text
        lineChart.setNoDataText("No data statistic for this psi");

        // enable value highlighting
        lineChart.setHighlightPerDragEnabled(true);
        lineChart.setHighlightPerTapEnabled(true);

        // enable touch gestures
        lineChart.setTouchEnabled(true);

        setLineChartScalingAndDragging();

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(true);

        setLegend();
    }

    private void setLineChartScalingAndDragging() {
        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);
    }

    private void setLegend() {
        Legend l = lineChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
    }

    /**
     * this method for set all data to line chart
     */
    @SuppressLint("ResourceType")
    private void setData() {
        psiByDate = PSIDatabaseImplementation.getPsiDate();

        for (int i = 0; i < psiByDate.getItemsArrayList().size(); i++) {
            addItemData(i);
        }

        setLineDataSet(R.color.colorPurple, arrayCentral, getLabel(R.string.string_central));
        setLineDataSet(R.color.colorGreen, arrayNorth, getLabel(R.string.string_north));
        setLineDataSet(R.color.colorOrange, arrayWest, getLabel(R.string.string_west));
        setLineDataSet(R.color.colorRed, arraySouth, getLabel(R.string.string_south));
        setLineDataSet(R.color.colorBlue, arrayEast, getLabel(R.string.string_east));
        setLineDataSet(R.color.colorYellow, arrayNational, getLabel(R.string.string_national));

        // create a data object with the datasets
        LineData data = new LineData(dataSets);

        // set data
        lineChart.setData(data);

        lineChart.animateX(2500);
        lineChart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = lineChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(R.color.colorGray);

        setAxisFormatter();

    }

    private void addItemData(int i) {
        arrayTime.add(ContentHelper.getDate("hh:mm:ss", psiByDate.getItemsArrayList().get(i).getTimestamp().substring(11, 19), "KK a"));
        float psiCentral = (float) getDataIndex(i).getPsiTwentyFourHourly().getCentral();
        float psiNorth = (float) getDataIndex(i).getPsiTwentyFourHourly().getNorth();
        float psiWest = (float) getDataIndex(i).getPsiTwentyFourHourly().getWest();
        float psiSouth = (float) getDataIndex(i).getPsiTwentyFourHourly().getSouth();
        float psiEast = (float) getDataIndex(i).getPsiTwentyFourHourly().getEast();
        float psiNational = (float) getDataIndex(i).getPsiTwentyFourHourly().getNational();

        arrayCentral.add(new Entry(i, psiCentral));
        arrayNorth.add(new Entry(i, psiNorth));
        arrayWest.add(new Entry(i, psiWest));
        arraySouth.add(new Entry(i, psiSouth));
        arrayEast.add(new Entry(i, psiEast));
        arrayNational.add(new Entry(i, psiNational));
    }

    private DataIndex getDataIndex(int i) {
        return psiByDate.getItemsArrayList().get(i).getDataIndex();
    }

    private String getLabel(@StringRes int label) {
        return getString(R.string.string_psi24h) + " " + getString(label);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void setAxisFormatter() {
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return arrayTime.get((int) value);
            }

        };

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
    }

    private void setLineDataSet(int color, ArrayList<Entry> entryArrayList, String label) {
        // create a dataset and give it a type
        LineDataSet lineDataSet = new LineDataSet(entryArrayList, label);
        lineDataSet.setColor(getResources().getColor(color));
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleSize(4f);
        lineDataSet.setFillAlpha(65);
        lineDataSet.setFillColor(color);
        lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        dataSets.add(lineDataSet); // add the datasets
    }

}
