package com.mm.android.searchResult.adapcter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mm.android.databinding.ItemRecyclerBinding
import com.mm.android.itemdetail.ItemDetailActivity
import com.mm.android.searchResult.data.ItemRank
import com.mm.android.sub.DialogActivity

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.Holder>() {
    lateinit var listData: ArrayList<ItemRank>
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
        val market = listData.get(position)
        holder.setMarket(market)
        val percent = holder.binding.changeText.text.toString()

        // 변동률에 따른 색상 변경
        if (percent.replace("%","").toDouble() > 0) {
            holder.binding.changeText.setTextColor(Color.rgb(81,107,244))
            holder.binding.changeImage.setColorFilter(Color.parseColor("#E8F2FF"))
        } else {
            holder.binding.changeText.setTextColor(Color.rgb(244,81,81))
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
                val intent = Intent(context, ItemDetailActivity::class.java)

//              액티비티로 인텐트 전달, adapterposition - 리싸이클러뷰에서 제공하는 위치 제공 메소드, https://velog.io/@appletorch/%EB%A6%AC%EC%82%AC%EC%9D%B4%ED%81%B4%EB%9F%AC%EB%B7%B0-%EC%95%84%EC%9D%B4%ED%85%9C-%ED%81%B4%EB%A6%AD-%EC%9D%B4%EB%B2%A4%ED%8A%B8
                val pGroup = listData.get(adapterPosition).pgroupName!!

                // 로딩화면 생성

                // retrofit 통신 보내기
                Log.d("test pGroup", "$pGroup")
                dialog = DialogActivity(parent.context)
                parent.context.startActivity(intent)
//                getDetailItem(intent, pGroup, parent)
            }
        }

        // 데이터 입력
        fun setMarket(itemRank: ItemRank) {
            binding.rank.text = "${itemRank.rankNum}"
            binding.item.text = "${itemRank.pgroupName}"
            binding.changeText.text = "${itemRank.percentage}"
        }

//        private fun getDetailItem(intent: Intent, pGroup: String, parent:ViewGroup) {
//            val retrofitAPI = RetrofitConnection.getInstance().create(RetrofitService::class.java)
//
//            retrofitAPI.getDetailItemList(pGroup)
//                .enqueue(object : Callback<DetailItemListResponse> {
//                    override fun onResponse(
//                        call: Call<DetailItemListResponse>,
//                        response: Response<DetailItemListResponse>
//                    ) {
//                        dialog.show()
//                        if (response.isSuccessful) {
//                            val data = response.body()
//                            Log.d("test data", "$data")
//                            intent.putStringArrayListExtra("itemDetail", data)
//                            dialog.dismiss()
//                            parent.context.startActivity(intent)
//                        } else {
//                            Toast.makeText(parent.context, "랭킹 서비스 실행 실패", Toast.LENGTH_LONG).show()
//                        }
//
//                    }
//
//                    override fun onFailure(call: Call<DetailItemListResponse>, t: Throwable) {
//                        t.printStackTrace()
//                    }
//                })
//        }

    }
}