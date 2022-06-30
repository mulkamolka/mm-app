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

        // 데이터를 불러온다
        val data = loadData()
        val spinnerData = ArrayList<String>()
        for (i in 0..data.size-1) {
            spinnerData.add(data.get(i).Item)
        }

        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, spinnerData)
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
    fun loadData() : ArrayList<DetailItem>{
        val data = ArrayList<DetailItem>()
        data.add(DetailItem("상추"))
        data.add(DetailItem("배추"))
        data.add(DetailItem("양배추"))

        return data
    }


}