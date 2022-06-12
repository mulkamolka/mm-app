package com.mm.android

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import com.mm.android.databinding.ActivitySearchBinding
import com.mm.android.databinding.FragmentHomeSearchBinding
import com.mm.android.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        val searchInput = binding.searchInput

        // 키보드 포커스를 요청
        searchInput.requestFocus()

        // 검색 이벤트
        searchInput.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {

            // 검색 버튼을 눌렀을 경우
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            // 텍스트가 바뀔 때 마다 호출
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("testt", "${newText}")
                return true
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}