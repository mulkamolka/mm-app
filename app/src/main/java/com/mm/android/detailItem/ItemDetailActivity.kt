package com.mm.android.detailItem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mm.android.R
import com.mm.android.databinding.ActivityItemDetailBinding
import com.mm.android.detailItem.adapter.TabLayoutAdapter
import com.mm.android.detailItem.data.DetailItem
import com.mm.android.detailItem.data.ItemNews
import com.mm.android.detailItem.data.ItemPrice
import com.mm.android.detailItem.fragment.ItemDetailTitleFragment
import com.mm.android.detailItem.normal.fragment.ItemDetailNormalFragment
import com.mm.android.detailItem.town.fragment.ItemDetailTownFragment

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var tabLayout1: TabLayout
    private lateinit var pager2: ViewPager2
    private lateinit var tabLayoutMedicator1: TabLayoutMediator


    val binding by lazy { ActivityItemDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 검색 리스트에서 선택된 세부 품목 받아오기
        setToDetailItemList()

        // 검색 리스트에서 뉴스 데이터 받아오기
        var itemNewsData =
            intent.getSerializableExtra("itemNewsListData") as ArrayList<ItemNews>
        var itemPriceData =
            intent.getSerializableExtra("DetailPriceListData") as ArrayList<ItemPrice>

        Log.d("test itemNews, itemPrice", "${itemNewsData}")
        Log.d("test itemNews, itemPrice", "${itemPriceData}")


        // 탭 레이아웃 - 토글
        var fragmentList = listOf(ItemDetailNormalFragment(itemNewsData, itemPriceData), ItemDetailTownFragment())
        val tabTitleList = listOf("  일 반  ", "  동 네  ")
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

    private fun setToDetailItemList() {
        Log.d("test setToDetailItemList()", "start")
        val itemDetailListData =
            intent.getSerializableExtra("itemDetailListData") as ArrayList<DetailItem>
        Log.d("test itemDetailListData", "${itemDetailListData}")

        val pGroup = intent.getStringExtra("pGroup")


        val bundle = Bundle()
        val fragment = ItemDetailTitleFragment()
        val transaction = supportFragmentManager.beginTransaction()

        bundle.putSerializable("itemDetailListData", itemDetailListData)
        bundle.putString("pGroup", pGroup)
        fragment.arguments = bundle
        transaction.add(R.id.fragmentContainerView11, fragment)
            .commit()
    }

//    private fun setToItemNews() {
//        val itemNewsData =
//            intent.getSerializableExtra("itemNewsListData") as ArrayList<ItemNews>
//        Log.d("test itemNewsListData", "${itemNewsData}")
//
//        val bundle = Bundle()
//        val fragment = ItemDetailTitleFragment()
//        val transaction = supportFragmentManager.beginTransaction()
//
//        bundle.putSerializable("itemDetailListData", itemNewsData)
//        fragment.arguments = bundle
//        transaction.add(R.id.fragmentContainerView11, fragment)
//            .commit()
//    }
}

