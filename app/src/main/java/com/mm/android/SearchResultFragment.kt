package com.mm.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm.android.databinding.FragmentHomeSearchBinding
import com.mm.android.databinding.FragmentSearchResultBinding

class SearchResultFragment : Fragment() {
    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        val view = binding.root

        // 데이터를 불러온다
        val data: MutableList<market> = loadData()
        var adapter = SearchResultAdapter()
        adapter.listData = data

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// 데이터를 불러오는 함수
fun loadData(): MutableList<market> {
    val data: MutableList<market> = mutableListOf()

    for (rank in 1..100) {
        val item = "바나나"
        var change = rank - 50.5
        var market = market(rank, item, change)
        data.add(market)
    }

    return data
}