package com.mm.android.detailItem.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemNews(
    val title: String?,
    val source_url: String?,
    val image_urls: String?,
    val publisher: String?,
    val registerd_at: String?,
    val description: String?
) : Parcelable
