package com.mm.android.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mm.android.address.AddressActivity
import com.mm.android.databinding.FragmentHomeOptionBinding

class HomeOptionFragment : Fragment() {
    private var _binding: FragmentHomeOptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeOptionBinding.inflate(inflater, container, false)
        val view = binding.root

        Log.d("test onCreateView", "ok")

        binding.addressButton.setOnClickListener {
            val intent = Intent(activity, AddressActivity::class.java)
            startActivity(intent)
        }

        val data = arguments?.getString("data")
        Log.d("test data", "${data}")
        binding.address.text = data

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}