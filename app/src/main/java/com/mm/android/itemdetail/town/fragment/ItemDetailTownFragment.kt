package com.mm.android.itemdetail.town.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm.android.databinding.FragmentItemDetailTownBinding
import com.mm.android.itemdetail.adapter.ItemDetailTownAdapter
import com.mm.android.itemdetail.data.Markets
import com.mm.android.itemdetail.data.MultiListType
import java.time.LocalDate

class ItemDetailTownFragment : Fragment() {

    private var _binding: FragmentItemDetailTownBinding? = null
    private val binding get() = _binding!!
    val multiListType: ArrayList<MultiListType> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailTownBinding.inflate(inflater, container, false)

        // 지도와 마켓 리스트 생성
        val marketsData: MutableList<Markets> = marketsLoad()
        var adapter = ItemDetailTownAdapter(activity)
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
        adapter.marketslist = marketsData
        adapter.multiListType = multiListType
        binding.townList.adapter = adapter
        binding.townList.layoutManager = LinearLayoutManager(activity)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    // 데이터 생성
    private fun marketsLoad(): MutableList<Markets> {
        val data: MutableList<Markets> = mutableListOf()

        for (i in 0..100) {
            val head: String = "가."
            val marketName: String = "OO마트"
            val kind: String = "대형마트"
            val location: String = "i00m"
            val price: Int = i * 10000
            val change: Int = i * 100
            val note: String = "국내산, 유정란, 30개"
            val upDown: Boolean = true
            val date: LocalDate = LocalDate.now()
            data.add(Markets(head, marketName, kind, location, price, change, upDown, note, date))
        }

        return data
    }
}