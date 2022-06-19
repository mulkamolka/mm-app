package com.mm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mm.android.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding by lazy { ActivityNewsDetailBinding.inflate(layoutInflater) }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 검색 리스트에서 선택된 아이템 프래그먼트 전달
        var title = intent.getStringExtra("title")
        var content = intent.getStringExtra("content")
        var url = intent.getStringExtra("url")

        var bundle = Bundle()
        val webviewOptionfragment = WebviewOptionFragment()
        val webviewfragment = WebviewFragment()
        val transaction = supportFragmentManager.beginTransaction()

        bundle.putString("title", "${title}")
        bundle.putString("content", "${content}")
        bundle.putString("url", "${url}")
        webviewOptionfragment.arguments = bundle
        webviewfragment.arguments = bundle
        transaction.add(R.id.fragmentContainerView12, webviewOptionfragment)
        transaction.add(R.id.fragmentContainerView13, webviewfragment)
        transaction.commit()


    }
}