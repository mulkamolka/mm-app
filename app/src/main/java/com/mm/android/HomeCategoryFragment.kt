package com.mm.android

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mm.android.databinding.FragmentHomeCategoryBinding

class HomeCategoryFragment : Fragment() {
    private var _binding: FragmentHomeCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.categoryA.setOnClickListener {
            val intent = Intent(activity, CategoryAActivity::class.java)
            startActivity(intent)
        }

        binding.categoryB.setOnClickListener {
            val intent = Intent(activity, CategoryBActivity::class.java)
            startActivity(intent)
        }

        binding.categoryC.setOnClickListener {
            val intent = Intent(activity, CategoryCActivity::class.java)
            startActivity(intent)
        }

        binding.categoryD.setOnClickListener {
            val intent = Intent(activity, CategoryDActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}