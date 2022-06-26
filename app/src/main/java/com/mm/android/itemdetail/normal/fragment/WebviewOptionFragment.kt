package com.mm.android.itemdetail.normal.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mm.android.databinding.FragmentWebviewOptionBinding
import com.mm.android.itemdetail.ItemDetailActivity

class WebviewOptionFragment : Fragment() {
    private var _binding: FragmentWebviewOptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebviewOptionBinding.inflate(inflater, container, false)

        val view = binding.root
        val newsBackButton = binding.newsBackButton
        val title = arguments?.getString("title")
        val content = arguments?.getString("content")

        // 뒤로가기 버튼
        newsBackButton.setOnClickListener {
            val intent = Intent(activity, ItemDetailActivity::class.java)

            // 스택 정리
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        binding.newsTitle.text = title
        binding.newsContent.text = content

        Log.d("test2 title", "${title}")
        Log.d("test2 content", "${content}")

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}