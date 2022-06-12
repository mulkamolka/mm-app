package com.mm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mm.android.databinding.ActivityCategoryDactivityBinding

class CategoryDActivity : AppCompatActivity() {

    val binding by lazy { ActivityCategoryDactivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}