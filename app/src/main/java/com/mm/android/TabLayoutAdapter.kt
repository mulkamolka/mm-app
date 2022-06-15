package com.mm.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabLayoutAdapter(fragmentActivity: FragmentActivity, private val fragmentList: List<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {

    // 화면에 보여줄 전체 프래그먼트 개수 지정
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    // position에 해당하는 프래그먼트 생성
    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }


}