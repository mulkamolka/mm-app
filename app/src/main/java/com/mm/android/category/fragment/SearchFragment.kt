package com.mm.android.category.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.mm.android.home.MainActivity
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
        val backButton = binding.backButton

        // 인텐트


        // 뒤로가기 버튼
        backButton.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)

            // 스택 정리
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        // 키보드 포커스를 요청
        searchInput.requestFocus()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 검색 이벤트
        binding.searchInput.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            // 검색 버튼을 눌렀을 경우
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            // 텍스트가 바뀔 때 마다 호출
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("testt", "hi ${newText}")
                var bundle = bundleOf("valueKey" to "${newText}")
                setFragmentResult("request", bundle)
                return true
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}