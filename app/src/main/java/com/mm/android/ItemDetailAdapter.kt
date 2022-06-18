package com.mm.android

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mm.android.databinding.DetailGraphItemRecyclerBinding
import com.mm.android.databinding.DetailNewsItemRecyclerBinding

class ItemDetailAdapter: RecyclerView.Adapter<Holder>() {
    var detail = ArrayList<Detail>()
    lateinit var newsList: MutableList<News>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        Log.d("test onCreateViewHolder", "Let's onCreateViewHolder")
        when (viewType) {
            Detail.TYPE_A -> {
                Log.d("test onBindViewHolder", "TYPE_ A ok")
                val binding = DetailGraphItemRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return Holder(binding)
            }
            Detail.TYPE_B -> {
                Log.d("test onBindViewHolder", "TYPE_ B ok")
                val binding = DetailNewsItemRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return Holder(binding)
            }
            else -> {
                Log.i("test MyAdapter", "Unrecognized viewType = $viewType")
                val binding = DetailNewsItemRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return Holder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Log.d("test onBindViewHolder", "Let's onBindViewHolder")
        when (holder.itemViewType) {
            Detail.TYPE_A -> {
                Log.d("test onBindViewHolder", "TYPE_A ${position}")
                holder.setGraph()
            }
            Detail.TYPE_B -> {
                Log.d("test onBindViewHolder", "TYPE_ B${position}")
                val newsData = newsList.get(position)
                holder.setNews(newsData)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("test getItemCount", "Let's getItemCount ${detail.size}")
        return detail.size
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("test getItemViewType", "Let's getItemViewType ${detail[position].type}")
        return detail[position].type
    }
}

class Holder : RecyclerView.ViewHolder {
    lateinit var bindingA: DetailGraphItemRecyclerBinding
    lateinit var bindingB: DetailNewsItemRecyclerBinding

    constructor(bindingA: DetailGraphItemRecyclerBinding) : super(bindingA.root) {
        this.bindingA = bindingA
    }

    constructor(bindingB: DetailNewsItemRecyclerBinding) : super(bindingB.root) {
        this.bindingB = bindingB
    }

    fun setGraph() {
        Log.d("test setGraph", "setGraph ok")
        bindingA.text3.text = "This is graph"
    }

    fun setNews(news: News) {
        Log.d("test setNews", "setNews ok")
        Glide.with(itemView.context).load("${news.image}").centerCrop().into(bindingB.imageView)
        bindingB.textView2.text = "${news.time}"
        bindingB.textView3.text = "${news.newsPaper}"
        bindingB.textView4.text = "${news.title}"
        bindingB.textView5.text = "${news.content}"
    }

}