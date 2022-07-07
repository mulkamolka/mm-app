package com.mm.android.detailItem.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemPrice(
    var id: String?,
    var price: String?,
    var date: String?
):Parcelable
