package com.mm.android.home.retrofit

import com.mm.android.category.data.Market
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemRank {
    @GET("category_item")
    fun getMarketItemData(
        @Query("category") category: String
    ) : Call<Market>
}