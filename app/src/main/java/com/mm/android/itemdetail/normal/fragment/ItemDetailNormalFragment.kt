package com.mm.android.itemdetail.normal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm.android.databinding.FragmentItemDetailNormalBinding
import com.mm.android.itemdetail.adapter.ItemDetailNormalAdapter
import com.mm.android.itemdetail.data.MultiListType
import com.mm.android.itemdetail.data.News


class ItemDetailNormalFragment : Fragment() {

    private var _binding: FragmentItemDetailNormalBinding? = null
    private val binding get() = _binding!!
    val multiListType: ArrayList<MultiListType> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailNormalBinding.inflate(inflater, container, false)


        // 그래프와 뉴스 리스트 생성
        val newsData:MutableList<News> = newsLoad()
        var adapter = ItemDetailNormalAdapter()
        multiListType.add(MultiListType(MultiListType.TYPE_A))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        multiListType.add(MultiListType(MultiListType.TYPE_B))
        adapter.newsList = newsData
        adapter.multiListType = multiListType
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
            val url = "https://kormedi.com/1403385/%EF%BB%BF%EB%B0%94%EB%82%98%EB%82%98-%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8%EC%99%80-%EA%B1%B4%EA%B0%95%EC%97%90-%EC%A2%8B%EC%9D%80-%EC%9D%B4%EC%9C%A0-7/"
            val time = System.currentTimeMillis()
            data.add(News(image, title, content, newsPaper, time, url))
        }
        return data
    }
}

