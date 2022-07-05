package com.mm.android.detailItem.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
// 트러블 슈팅 1 - 물음표를 붇여주지 않으면 값이 전달되지 않는다.
data class DetailItem(
    var categoryCode: String?,
    var kind: String?,
    var rank: String?,
    var unit: String?,
    var pgroup: String?,
    var description: String?
) : Parcelable
