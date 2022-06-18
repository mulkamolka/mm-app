package com.mm.android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm.android.databinding.FragmentItemDetailNormalBinding


class ItemDetailNormalFragment : Fragment() {

    private var _binding: FragmentItemDetailNormalBinding? = null
    private val binding get() = _binding!!
    val detail: ArrayList<Detail> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailNormalBinding.inflate(inflater, container, false)


        // 그래프와 뉴스 리스트 생성
        val newsData:MutableList<News> = newsLoad()
        var adapter = ItemDetailAdapter()
        detail.add(Detail(Detail.TYPE_A))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        detail.add(Detail(Detail.TYPE_B))
        adapter.newsList = newsData
        adapter.detail = detail
        binding.normalList.adapter = adapter
        binding.normalList.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    // 뉴스 데이터
    private fun newsLoad(): MutableList<News> {
        val data: MutableList<News> = mutableListOf()

        for (i in 0..100) {
            val image = "https://health.chosun.com/site/data/img_dir/2021/01/27/2021012700739_0.jpg"
            val title = "${i}번째 바나나"
            val content = "바나나는 다이어트에 좋은 식품이다. 중간 크기의 바나나 한 개의 열량은 110칼로리인데 이를 통해 건강에 좋은..."
            val newsPaper = "00일보"
            val time = System.currentTimeMillis()
            data.add(News(image, title, content, newsPaper, time))
        }
        return data
    }
}

