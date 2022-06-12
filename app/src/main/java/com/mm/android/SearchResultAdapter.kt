package com.mm.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mm.android.databinding.ItemRecyclerBinding

class SearchResultAdapter : RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<market>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val market = listData.get(position)
        holder.setMarket(market)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}

class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setMarket(market: market) {
        binding.rank.text = "${market.rank}"
        binding.item.text = "${market.item}"
        binding.change.text = "${market.change}"
    }
}