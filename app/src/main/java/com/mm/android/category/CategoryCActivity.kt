package com.mm.android.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mm.android.databinding.ActivityCategoryCactivityBinding

class CategoryCActivity : AppCompatActivity() {

    val binding by lazy { ActivityCategoryCactivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}