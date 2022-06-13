package com.mm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mm.android.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {

    val binding by lazy { ActivityItemDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.test.text = intent.getStringExtra("test")
    }
}