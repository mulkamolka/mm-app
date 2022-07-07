package com.mm.android.searchResult.adapcter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mm.android.databinding.ItemRecyclerBinding
import com.mm.android.detailItem.ItemDetailActivity
import com.mm.android.detailItem.data.DetailItemListResponse
import com.mm.android.detailItem.data.ItemNewsListResponse
import com.mm.android.detailItem.data.ItemPriceListResponse
import com.mm.android.retrofit.RetrofitConnection
import com.mm.android.retrofit.RetrofitService
import com.mm.android.searchResult.data.ItemRank
import com.mm.android.sub.DialogActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.Holder>() {
    lateinit var listData: ArrayList<ItemRank>
    var detailItemOk = false
    var itemNewsOk = false
    var ItemPriceOk = false
    var bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        // 인자 값을 두개 넣어줘야 함 - Holder 클래스 parent 인자 추가
        return Holder(binding, parent)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var market = listData.get(position)
        holder.setMarket(market)
        var percent = holder.binding.changeText.text.toString()

        // 변동률에 따른 색상 변경
        if (percent.replace("%", "").toDouble() < 0) {
            holder.binding.changeText.setTextColor(Color.rgb(81, 107, 244))
            holder.binding.changeImage.setColorFilter(Color.parseColor("#E8F2FF"))
        } else {
            holder.binding.changeText.setTextColor(Color.rgb(244, 81, 81))
            holder.binding.changeImage.setColorFilter(Color.parseColor("#FFE8E8"))
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    // intent 구현을 위해 parent를 인자로 받음, inner class로 구현
    inner class Holder(val binding: ItemRecyclerBinding, parent: ViewGroup) :


        RecyclerView.ViewHolder(binding.root) {
        lateinit var dialog: DialogActivity

        init {
            val context = parent.context

            binding.root.setOnClickListener {
                var intent = Intent(context, ItemDetailActivity::class.java)

//              액티비티로 인텐트 전달, adapterposition - 리싸이클러뷰에서 제공하는 위치 제공 메소드, https://velog.io/@appletorch/%EB%A6%AC%EC%82%AC%EC%9D%B4%ED%81%B4%EB%9F%AC%EB%B7%B0-%EC%95%84%EC%9D%B4%ED%85%9C-%ED%81%B4%EB%A6%AD-%EC%9D%B4%EB%B2%A4%ED%8A%B8
                var pGroup = listData.get(adapterPosition).pgroupName!!

                // retrofit 통신 보내기
                Log.d("test pGroup", "$pGroup")
                dialog = DialogActivity(parent.context)
//                parent.context.startActivity(intent)
                dialog.show()
                getDetailItem(intent, pGroup, parent)
                getItemNews(intent, pGroup, parent)
            }
        }

        // 데이터 입력
        fun setMarket(itemRank: ItemRank) {
            binding.rank.text = "${itemRank.rankNum}"
            binding.item.text = "${itemRank.pgroupName}"
            binding.changeText.text = "${itemRank.percentage}"
        }

        // 세부 품목 받아오기
        private fun getDetailItem(intent: Intent, pGroup: String, parent: ViewGroup) {
            var retrofitAPI =
                RetrofitConnection.getInstanceBack().create(RetrofitService::class.java)

            retrofitAPI.getDetailItemList(pGroup)
                .enqueue(object : Callback<DetailItemListResponse> {
                    override fun onResponse(
                        call: Call<DetailItemListResponse>,
                        response: Response<DetailItemListResponse>
                    ) {
                        dialog.show()
                        if (response.isSuccessful) {
                            val data = response.body()
                            detailItemOk = true
                            Log.d("test data", "$data")
                            Log.d("test cCode", "${data!![0].categoryCode}")
                            intent.putExtra("itemDetailListData", data)
                            intent.putExtra("pGroup", pGroup)
                            getItemPrice(intent, data!![0].categoryCode, parent)
//                            if(detailItemOk && itemNewsOk) {
//                                dialog.dismiss()
//                                parent.context.startActivity(intent)
//                            }
                        } else {
                            Toast.makeText(parent.context, "품목 서비스 실행 실패", Toast.LENGTH_LONG).show()
                        }

                    }

                    override fun onFailure(call: Call<DetailItemListResponse>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(parent.context, "품목 서비스 실행 실패", Toast.LENGTH_LONG).show()
                    }
                })
        }

        // 뉴스 데이터 받아오기
        private fun getItemNews(intent: Intent, pGroup: String, parent: ViewGroup) {
            var retrofitAPI =
                RetrofitConnection.getInstanceData().create(RetrofitService::class.java)

            retrofitAPI.getDetailItemNews(pGroup)
                .enqueue(object : Callback<ItemNewsListResponse> {
                    override fun onResponse(
                        call: Call<ItemNewsListResponse>,
                        listResponse: Response<ItemNewsListResponse>
                    ) {
                        dialog.show()
                        if (listResponse.isSuccessful) {
                            val data = listResponse.body()
                            itemNewsOk = true
                            Log.d("test data", "$data")
                            intent.putExtra("itemNewsListData", data)
                            if (ItemPriceOk) {
                                ItemPriceOk = false
                                itemNewsOk = false
                                detailItemOk = false
                                dialog.dismiss()
                                parent.context.startActivity(intent)
                            }
                        } else {
                            Toast.makeText(parent.context, "뉴스 서비스 실행 실패", Toast.LENGTH_LONG).show()
                        }

                    }

                    override fun onFailure(call: Call<ItemNewsListResponse>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(parent.context, "뉴스 서비스 실행 실패", Toast.LENGTH_LONG).show()
                    }
                })
        }

        // 가격 데이터 받아오기
        private fun getItemPrice(intent: Intent, cCode: String?, parent: ViewGroup) {
            var retrofitAPI =
                RetrofitConnection.getInstanceBack().create(RetrofitService::class.java)

            retrofitAPI.getDetailItemPricekData(cCode!!)
                .enqueue(object : Callback<ItemPriceListResponse> {
                    override fun onResponse(
                        call: Call<ItemPriceListResponse>,
                        response: Response<ItemPriceListResponse>
                    ) {
                        dialog.show()
                        if (response.isSuccessful) {
                            ItemPriceOk = true
                            val data = response.body()
                            intent.putExtra("DetailPriceListData", data)
                            Log.d("test getItemPrice", "${data}")
                            if (itemNewsOk) {
                                ItemPriceOk = false
                                itemNewsOk = false
                                detailItemOk = false
                                dialog.dismiss()
                                parent.context.startActivity(intent)
                            }
                        } else {
                            Toast.makeText(parent.context, "그래프 서비스 실행 실패", Toast.LENGTH_LONG)
                                .show()
                        }

                    }

                    override fun onFailure(call: Call<ItemPriceListResponse>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(parent.context, "그래프 서비스 실행 실패", Toast.LENGTH_LONG).show()
                    }
                })
        }

    }
}