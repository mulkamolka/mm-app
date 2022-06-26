package com.mm.android.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mm.android.databinding.ActivityCategoryAactivityBinding

class CategoryAActivity : AppCompatActivity() {

    val binding by lazy { ActivityCategoryAactivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}