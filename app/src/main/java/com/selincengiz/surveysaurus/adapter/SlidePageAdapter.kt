package com.selincengiz.surveysaurus.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class SlidePageAdapter (list: ArrayList<Fragment>,manager: FragmentManager ):FragmentPagerAdapter(manager){
   private val fragmentList:ArrayList<Fragment> = list



    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

}
