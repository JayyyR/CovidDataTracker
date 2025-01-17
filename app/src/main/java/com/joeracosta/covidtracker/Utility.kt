package com.joeracosta.covidtracker

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addToComposite(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

@BindingAdapter("xAxisFormatter")
fun LineChart.setXAxisLabelFormatter(xAxisFormatter: (Float) -> String) {
    // the labels that should be drawn on the XAxis
    val formatter: ValueFormatter =
        object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String {
                return xAxisFormatter.invoke(value)
            }
        }
    val xAxis = this.xAxis
    xAxis?.valueFormatter = formatter
}

@BindingAdapter("radioGroupIndexListener")
fun RadioGroup.setIndexListener(indexClickedListener: (Int) -> Unit) {
    this.setOnCheckedChangeListener { group, checkedId ->
        val radioButton = group.findViewById<RadioButton>(checkedId)
        val index = group.indexOfChild(radioButton)

        if (radioButton?.isChecked == true) {
            indexClickedListener.invoke(index)
        }
    }
}

@BindingAdapter("backgroundResource")
fun setBackgroundResource(view: View, backgroundResource: Int) {
    view.setBackgroundResource(backgroundResource)
}

object TimeUtil {
    const val ALL_TIME_DAYS = 18250
    const val SIX_MONTHS_DAYS = 182
    const val THREE_MONTHS_DAYS = 90
    const val ONE_MONTH_DAYS = 30
    const val TWO_WEEKS_DAYS = 14
    const val FIVE_DAYS = 5

    const val DAY_MILLIS = 24 * 60 * 60 * 1000.toLong()
}