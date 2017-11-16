package com.imdglobalservices.psi.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.imdglobalservices.psi.R
import com.imdglobalservices.psi.database.PSIDatabaseImplementation
import com.imdglobalservices.psi.models.DataIndex
import com.imdglobalservices.psi.models.PSIDate
import com.imdglobalservices.psi.utils.ContentHelper
import kotlinx.android.synthetic.main.fragment_statistic_psi.*
import java.util.*

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.16.11
 */
class PSIStatisticFragment : Fragment(), OnChartValueSelectedListener {
    private val arrayTime = ArrayList<String>()
    private val arrayCentral = ArrayList<Entry>()
    private val arrayNorth = ArrayList<Entry>()
    private val arrayWest = ArrayList<Entry>()
    private val arraySouth = ArrayList<Entry>()
    private val arrayEast = ArrayList<Entry>()
    private val arrayNational = ArrayList<Entry>()
    private val dataSets = ArrayList<ILineDataSet>()
    private var psiByDate: PSIDate? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_statistic_psi, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initline_chart_statistic()
        setData()
    }

    /**
     * this method for initialize data
     */
    private fun initline_chart_statistic() {

        line_chart_statistic?.setOnChartValueSelectedListener(this)
        line_chart_statistic?.description?.isEnabled = false
        line_chart_statistic?.setDrawBorders(true)
        line_chart_statistic?.axisLeft?.isEnabled = false
        line_chart_statistic?.axisRight?.setDrawAxisLine(false)
        line_chart_statistic?.axisRight?.setDrawGridLines(false)
        line_chart_statistic?.xAxis?.setDrawAxisLine(false)
        line_chart_statistic?.xAxis?.setDrawGridLines(false)

        // no description text
        line_chart_statistic?.setNoDataText("No data statistic for this psi")

        // enable value highlighting
        line_chart_statistic?.isHighlightPerDragEnabled = true
        line_chart_statistic?.isHighlightPerTapEnabled = true

        // enable touch gestures
        line_chart_statistic?.setTouchEnabled(true)

        setline_chart_statisticScalingAndDragging()

        // if disabled, scaling can be done on x- and y-axis separately
        line_chart_statistic?.setPinchZoom(true)

        setLegend()
    }

    private fun setline_chart_statisticScalingAndDragging() {
        // enable scaling and dragging
        line_chart_statistic?.isDragEnabled = true
        line_chart_statistic?.setScaleEnabled(true)
        line_chart_statistic?.setDrawGridBackground(false)
    }

    private fun setLegend() {
        val l = line_chart_statistic?.legend
        l?.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l?.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l?.orientation = Legend.LegendOrientation.VERTICAL
        l?.setDrawInside(false)
    }

    /**
     * this method for set all data to line chart
     */
    @SuppressLint("ResourceType")
    private fun setData() {
        psiByDate = PSIDatabaseImplementation.psiDate

        for (i in 0 until psiByDate?.itemsArrayList!!.size) {
            addItemData(i)
        }

        setLineDataSet(R.color.colorPurple, arrayCentral, getLabel(R.string.string_central))
        setLineDataSet(R.color.colorGreen, arrayNorth, getLabel(R.string.string_north))
        setLineDataSet(R.color.colorOrange, arrayWest, getLabel(R.string.string_west))
        setLineDataSet(R.color.colorRed, arraySouth, getLabel(R.string.string_south))
        setLineDataSet(R.color.colorBlue, arrayEast, getLabel(R.string.string_east))
        setLineDataSet(R.color.colorYellow, arrayNational, getLabel(R.string.string_national))

        // create a data object with the datasets
        val data = LineData(dataSets)

        // set data
        line_chart_statistic?.data = data

        line_chart_statistic?.animateX(2500)
        line_chart_statistic?.invalidate()

        // get the legend (only possible after setting data)
        val l = line_chart_statistic?.legend

        // modify the legend ...
        l?.form = Legend.LegendForm.LINE
        l?.textColor = R.color.colorGray

        setAxisFormatter()

    }

    private fun addItemData(i: Int) {
        psiByDate?.itemsArrayList?.get(i)?.timestamp?.substring(11, 19)?.let { ContentHelper.getDate("hh:mm:ss", it, "KK a") }?.let { arrayTime.add(it) }
        val psiCentral = getDataIndex(i)?.psiTwentyFourHourly?.central!!.toFloat()
        val psiNorth = getDataIndex(i)?.psiTwentyFourHourly?.north!!.toFloat()
        val psiWest = getDataIndex(i)?.psiTwentyFourHourly?.west!!.toFloat()
        val psiSouth = getDataIndex(i)?.psiTwentyFourHourly?.south!!.toFloat()
        val psiEast = getDataIndex(i)?.psiTwentyFourHourly?.east!!.toFloat()
        val psiNational = getDataIndex(i)?.psiTwentyFourHourly?.national!!.toFloat()

        arrayCentral.add(Entry(i.toFloat(), psiCentral))
        arrayNorth.add(Entry(i.toFloat(), psiNorth))
        arrayWest.add(Entry(i.toFloat(), psiWest))
        arraySouth.add(Entry(i.toFloat(), psiSouth))
        arrayEast.add(Entry(i.toFloat(), psiEast))
        arrayNational.add(Entry(i.toFloat(), psiNational))
    }

    private fun getDataIndex(i: Int): DataIndex? {
        return psiByDate?.itemsArrayList?.get(i)?.dataIndex
    }

    private fun getLabel(@StringRes label: Int): String {
        return getString(R.string.string_psi24h) + " " + getString(label)
    }

    override fun onValueSelected(e: Entry, h: Highlight) {

    }

    override fun onNothingSelected() {

    }

    private fun setAxisFormatter() {
        val formatter = IAxisValueFormatter { value, axis -> arrayTime[value.toInt()] }

        val xAxis = line_chart_statistic?.xAxis
        xAxis?.granularity = 1f // minimum axis-step (interval) is 1
        xAxis?.valueFormatter = formatter
    }

    private fun setLineDataSet(color: Int, entryArrayList: ArrayList<Entry>, label: String) {
        // create a dataset and give it a type
        val lineDataSet = LineDataSet(entryArrayList, label)
        lineDataSet.color = resources.getColor(color)
        lineDataSet.setCircleColor(color)
        lineDataSet.lineWidth = 2f
        lineDataSet.circleSize = 4f
        lineDataSet.fillAlpha = 65
        lineDataSet.fillColor = color
        lineDataSet.highLightColor = Color.rgb(244, 117, 117)
        dataSets.add(lineDataSet) // add the datasets
    }

}
