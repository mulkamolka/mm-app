package com.mm.android.searchResult.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm.android.searchResult.adapcter.SearchResultAdapter
import com.mm.android.databinding.FragmentSearchResultBinding
import com.mm.android.searchResult.data.ItemRank

class SearchResultFragment : Fragment() {
    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!
    private var adapter = SearchResultAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        val view = binding.root

        val data = loadData()
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

    // 데이터를 불러온다
    private fun loadData(): ArrayList<ItemRank> {
        val tempData = ArrayList<ItemRank>()
        Log.d("test loadData()", "ok")
        val data = arguments?.getSerializable("itemRankListData") as ArrayList<ItemRank>
        tempData.addAll(data)
        Log.d("test itemRankListData", "${data}")

        return tempData
    }

    // 검색 함수 구현
    private fun search(string: String) {

        adapter.listData.clear()

        val searchResultData = loadData()
        Log.d("test search", "${searchResultData}")


        if (string?.length == 0) {
            adapter.listData.addAll(searchResultData)
            Log.d("test search", "ok")
        }
        else {
            for (i in 0..searchResultData.size - 1) {
                if (searchResultData.get(i).pgroupName!!.contains(string)) {
                    adapter.listData.add(searchResultData.get(i))
                }
            }

        }
        // 어댑터 데이터 갱신
        adapter.notifyDataSetChanged()
    }
}


