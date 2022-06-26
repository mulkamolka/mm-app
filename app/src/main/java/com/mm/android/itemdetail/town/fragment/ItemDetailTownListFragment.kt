package com.mm.android.itemdetail.town.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mm.android.databinding.FragmentItemDetailTownListBinding

class ItemDetailTownListFragment : Fragment() {
    private var _binding: FragmentItemDetailTownListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = binding.root

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}