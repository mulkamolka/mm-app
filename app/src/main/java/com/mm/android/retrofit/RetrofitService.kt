package com.mm.android.retrofit

import com.mm.android.searchResult.data.AllItemRankListResponse
import com.mm.android.searchResult.data.CategoryItemRankListResponse
import com.mm.android.itemdetail.data.DetailItemListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitService {

    // item rank - all
    @GET("list/1")
    fun getAllItemRankData() : Call<AllItemRankListResponse>

    // item rank - category
    @GET("list/2/{category}") // URI 주소 입력 필요
    fun getCategoryItemRankData(@Path("category") category:String) : Call<CategoryItemRankListResponse>

    // Detail Item
    @GET("list/3/{pGroup}") // URI 주소 입력 필요
    fun getDetailItemList(@Path("pGroup") pGroup:String) : Call<DetailItemListResponse>

//    // Detail Item Price
//    @POST("/list/4/{cCode}") // URI 주소 입력 필요
//    fun getDetailItemPricekData(@Path("cCode") cCode:String) : Call<ItemRankListResponse>
//
//    // Market Lat, Long
//    @POST("/list/5/{latuitude, longitude}") // URI 주소 입력 필요
//    fun getMarketData(@Path("latuitude, longitude") latuitude:Double, longitude:Double) : Call<ItemRankListResponse>
}