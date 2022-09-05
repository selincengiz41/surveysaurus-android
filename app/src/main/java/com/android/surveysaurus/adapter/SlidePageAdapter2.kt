package com.android.surveysaurus.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SlidePageAdapter2(list: ArrayList<Fragment>, manager: FragmentManager) :
    FragmentPagerAdapter(manager) {
    private val fragmentList: ArrayList<Fragment> = list


    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {

        return fragmentList[position]
    }


}