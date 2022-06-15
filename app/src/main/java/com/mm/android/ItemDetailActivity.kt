package com.mm.android

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mm.android.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var tabLayout1: TabLayout
    private lateinit var pager2: ViewPager2
    private lateinit var tabLayoutMedicator1: TabLayoutMediator

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

        // 탭 레이아웃 - 토글
        val fragmentList = listOf(ItemDetailNormalFragment(), ItemDetailTownFragment())
        val tabTitleList = listOf("일반", "동네")
        tabLayout1 = binding.tabLayout1
        pager2 = binding.viewPager1
        initView(fragmentList, tabTitleList)

    }

    private fun initView(fragmentList: List<Fragment>, tabTitleList: List<String>) {
        pager2.offscreenPageLimit = 2
        pager2.adapter = TabLayoutAdapter(this, fragmentList)

        tabLayoutMedicator1 = TabLayoutMediator(tabLayout1, pager2) { tab, position ->
            tab.text = tabTitleList[position]

        }

        tabLayoutMedicator1.attach()
    }
}