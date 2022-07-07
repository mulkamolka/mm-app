package com.mm.android.detailItem.normal.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm.android.databinding.FragmentItemDetailNormalBinding
import com.mm.android.detailItem.adapter.ItemDetailNormalAdapter
import com.mm.android.detailItem.data.ItemNews
import com.mm.android.detailItem.data.ItemPrice
import com.mm.android.detailItem.data.MultiListType


class ItemDetailNormalFragment(val newsData:ArrayList<ItemNews>, val priceData:ArrayList<ItemPrice>) : Fragment() {

    private var _binding: FragmentItemDetailNormalBinding? = null
    private val binding get() = _binding!!
    val multiListType: ArrayList<MultiListType> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailNormalBinding.inflate(inflater, container, false)


        // 그래프 데이터 생성
        var adapter = ItemDetailNormalAdapter()
        multiListType.add(MultiListType(MultiListType.TYPE_A))

        // 뉴스 데이터 생성
        for (i in 0..newsData.size-1) {
            Log.d("test size", "${newsData.size-1}")
            multiListType.add(MultiListType(MultiListType.TYPE_B))
        }

        adapter.newsList = newsData
        adapter.priceList = priceData
        adapter.multiListType = multiListType
        binding.normalList.adapter = adapter
        binding.normalList.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}

