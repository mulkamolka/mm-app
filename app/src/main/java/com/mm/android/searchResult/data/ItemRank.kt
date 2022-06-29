package com.mm.android.searchResult.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemRank(
    var rankNum: Int?, var pgroupName: String?, var percentage: String?) : Parcelable