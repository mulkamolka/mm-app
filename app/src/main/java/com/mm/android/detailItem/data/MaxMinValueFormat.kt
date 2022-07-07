package com.mm.android.detailItem.data


import android.util.Log
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


class MaxMinValueFormat(
    val yMax: Float, val yMin: Float
) : IndexAxisValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        var result:String = ""

        if (value == yMax) {
            Log.d("test min", "ok")
            result = "↓"
        } else if (value == yMin) {
            Log.d("test min", "ok")
            result =  "↓"
        }


        return result
    }
}