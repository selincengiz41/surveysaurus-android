package com.selincengiz.surveysaurus.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.selincengiz.surveysaurus.R
import com.selincengiz.surveysaurus.adapter.SlidePageAdapter
import com.selincengiz.surveysaurus.databinding.FragmentHomeBinding
import com.selincengiz.surveysaurus.databinding.FragmentViewPagerBinding


class ViewPagerFragment : Fragment() {
    private  var _binding: FragmentViewPagerBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root
        val fragmentList= arrayListOf<Fragment>(
            HomeFragment(),
            SurveysFragment()
        )
        val adapter= SlidePageAdapter(fragmentList,requireActivity().supportFragmentManager)
        val viewPager =binding.viewPager
        viewPager.adapter=adapter


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentList= arrayListOf<Fragment>(
            HomeFragment(),
            SurveysFragment()
        )
        val adapter= SlidePageAdapter(fragmentList,requireActivity().supportFragmentManager)
        val viewPager =binding.viewPager
        viewPager.adapter=adapter

    }


    override fun onResume() {
        super.onResume()
        val fragmentList= arrayListOf<Fragment>(
            HomeFragment(),
            SurveysFragment()
        )
        val adapter= SlidePageAdapter(fragmentList,requireActivity().supportFragmentManager)
        binding.viewPager.adapter = adapter

    }

    override fun onPause() {
        super.onPause()
        binding.viewPager.adapter = null //I don't remember why I did this, I gues
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }


}