package com.mm.android

import java.time.LocalDate

data class Markets(
    val head: String, val marketName: String, val kind: String,
    val location: String, val price: Int, val change: Int, val upDown: Boolean,
    val note: String, val date: LocalDate
)
