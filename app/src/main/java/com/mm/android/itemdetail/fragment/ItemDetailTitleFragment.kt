package com.mm.android.itemdetail.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.mm.android.R
import com.mm.android.home.MainActivity
import com.mm.android.databinding.FragmentItemDetailTitleBinding
import com.mm.android.itemdetail.data.DetailItem
import com.mm.android.searchResult.data.ItemRank

class ItemDetailTitleFragment : Fragment() {

    private var _binding: FragmentItemDetailTitleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemDetailTitleBinding.inflate(inflater, container, false)
        val view = binding.root
        val backButton = binding.backButton

        // 데이터 읽기 및 스피너 데이터 입력
        val data = loadData() // 트러블 슈팅 1 - 여기에서는 null 값으로 뜸
        val spinnerData = ArrayList<String>()
        for (i in 0..data.size - 1) {
            val temp = data.get(i)
            val desc = data.get(i).description
            val descData = loadDesc(desc!!)
            if (descData != temp.unit) {
                spinnerData.add("${temp.kind + " / " + temp.rank + " / " + temp.unit + " / " + descData}")
            } else {
                spinnerData.add("${temp.kind + " / " + temp.rank + " / " + temp.unit}")
            }
        }
        Log.d("test spinnerData", "${spinnerData}")

        // 데이터 읽기 및 itemName 변경
        binding.detailItemName.text = arguments?.getString("pGroup")


        val adapter =
            ArrayAdapter<String>(requireContext(), R.layout.custom_list_item_1, spinnerData)
        binding.spinner.adapter = adapter

        // 뒤로가기 버튼
        backButton.setOnClickListener {
            activity?.finish()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 임시 함수
    private fun loadData(): ArrayList<DetailItem> {
        val tempData = arguments?.getSerializable("itemDetailListData") as ArrayList<DetailItem>
        Log.d("test itemDetailListData", "${tempData}")
        return tempData
    }

    private fun loadDesc(data: String): String {
        val descData: String
        val splitData = data.split('(')[1]
        descData = splitData.split(')')[0]

        Log.d("test loadDesc()", "${descData}")
        return descData
    }

}