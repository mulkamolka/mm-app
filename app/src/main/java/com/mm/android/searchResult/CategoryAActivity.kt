package com.mm.android.searchResult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mm.android.R
import com.mm.android.searchResult.fragment.SearchResultFragment
import com.mm.android.databinding.ActivityCategoryAactivityBinding
import com.mm.android.searchResult.data.ItemRank

class CategoryAActivity : AppCompatActivity() {

    val binding by lazy { ActivityCategoryAactivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val itemRankListData = intent.getSerializableExtra("itemRankListData") as ArrayList<ItemRank>
        Log.d("test itemRankListData", "${itemRankListData}")

        val bundle = Bundle()
        val fragment = SearchResultFragment()
        val transaction = supportFragmentManager.beginTransaction()

        bundle.putSerializable("itemRankListData", itemRankListData)
        fragment.arguments = bundle
        transaction.add(R.id.fragmentContainerView3, fragment)
            .commit()
    }
}