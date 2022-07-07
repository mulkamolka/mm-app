package com.mm.android.detailItem.data

import android.util.Log
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


class DateAxisValueFormat(val priceList:ArrayList<ItemPrice>) : IndexAxisValueFormatter() {
    override fun getFormattedValue(value: Float): String {

        var date = priceList.get(value.toInt()).date
        Log.d("test Date", "${date}")
        return date!!.substring(2)
    }
}