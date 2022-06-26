package com.mm.android.itemdetail.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mm.android.home.MainActivity
import com.mm.android.databinding.FragmentItemDetailTitleBinding

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

        // 뒤로가기 버튼
        backButton.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)

            // 스택 정리
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        Log.d("test Item Detail", "ok")
        val data = arguments?.getString("itemName")
        binding.itemName.text = data
        Log.d("test itemName", "${data}")


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}