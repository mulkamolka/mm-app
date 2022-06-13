package com.mm.android

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mm.android.databinding.ItemRecyclerBinding

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.Holder>() {
    var listData = mutableListOf<market>()
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

        // 변동률에 따른 색상 변경
        if (holder.binding.change.text.toString().toDouble() > 0) {
            holder.binding.change.setTextColor(Color.rgb(81,107,244))
            holder.binding.change.setBackgroundColor(Color.rgb(232,242,255))
        } else {
            holder.binding.change.setTextColor(Color.rgb(244,81,81))
            holder.binding.change.setBackgroundColor(Color.rgb(255,232,232))
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    // intent 구현을 위해 parent를 인자로 받음, inner class로 구현
    inner class Holder(val binding: ItemRecyclerBinding, parent: ViewGroup) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            val context = parent.context


            binding.root.setOnClickListener {
                val intent = Intent(context, ItemDetailActivity::class.java)

                // adapterposition - 리싸이클러뷰에서 제공하는 위치 제공 메소드, https://velog.io/@appletorch/%EB%A6%AC%EC%82%AC%EC%9D%B4%ED%81%B4%EB%9F%AC%EB%B7%B0-%EC%95%84%EC%9D%B4%ED%85%9C-%ED%81%B4%EB%A6%AD-%EC%9D%B4%EB%B2%A4%ED%8A%B8
                val item = listData.get(adapterPosition)
                intent.putExtra("test", "${item}")
                context.startActivity(intent)
            }
        }

        // 데이터 입력
        fun setMarket(market: market) {
            binding.rank.text = "${market.rank}"
            binding.item.text = "${market.item}"
            binding.change.text = "${market.change}"
        }
    }
}