package com.mm.android.category.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm.android.category.adapcter.SearchResultAdapter
import com.mm.android.databinding.FragmentSearchResultBinding
import com.mm.android.category.data.Market

class SearchResultFragment : Fragment() {
    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!
    private var adapter = SearchResultAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        val view = binding.root

        // 데이터를 불러온다
        val data: MutableList<Market> = loadData()
        adapter.listData = data

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("request") { Key, bundle ->
            bundle.getString("valueKey")?.let {
                Log.d("testt", "yes ${it}")
                search(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 데이터를 불러오는 함수
    fun loadData(): MutableList<Market> {
        val data: MutableList<Market> = mutableListOf()

        for (rank in 1..100) {
            val item = "바나나"
            var change = rank - 50.5
            var market = Market(rank, item, change)
            data.add(market)
        }

        return data
    }

    // 검색 함수 구현
    fun search(string: String) {
        adapter.listData.clear()

        var data = loadData()

        if (string?.length == 0) {
            adapter.listData.addAll(data)

        } else {
            for (i in 0..data.size-1) {
                if (data.get(i).item.contains(string)) {
                    adapter.listData.add(data.get(i))
                }
            }
        }

        // 어댑터 데이터 갱신
        adapter.notifyDataSetChanged()
    }
}


