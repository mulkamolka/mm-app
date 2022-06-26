package com.mm.android.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mm.android.databinding.ActivityCategoryBactivityBinding

class CategoryBActivity : AppCompatActivity() {

    val binding by lazy { ActivityCategoryBactivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}