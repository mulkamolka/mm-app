package com.mm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.mm.android.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {

    val binding by lazy { ActivityItemDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 검색 리스트에서 선택된 아이템 프래그먼트 전달
        var itemName = intent.getStringExtra("test")
        Log.d("testt", "${itemName}")
        val bundle = Bundle()
        val fragment = ItemDetailTitleFragment()
        val transaction = supportFragmentManager.beginTransaction()

        bundle.putString("itemName", itemName)
        fragment.arguments = bundle
        transaction.add(R.id.fragmentContainerView11, fragment)
        transaction.commit()
    }
}