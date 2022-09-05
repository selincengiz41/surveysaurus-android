package com.android.surveysaurus.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.android.surveysaurus.adapter.SlidePageAdapter2
import com.android.surveysaurus.databinding.FragmentViewPager2Binding


class ViewPagerFragment2 : Fragment() {
    private var _binding: FragmentViewPager2Binding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPager2Binding.inflate(inflater, container, false)
        val view = binding.root

        arguments?.let {
            val survey = ViewPagerFragment2Args.fromBundle(it).surveyModel
          val isFilled = ViewPagerFragment2Args.fromBundle(it).isFilled
            val fragmentList = arrayListOf<Fragment>(
                FillSurveyFragment(survey,isFilled),
                CommentFragment()
            )
            val adapter2 = SlidePageAdapter2(fragmentList, requireActivity().supportFragmentManager)
            val viewPager = binding.viewPager2
            viewPager.adapter = adapter2


        }
        // Inflate the layout for this fragment




        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val survey = ViewPagerFragment2Args.fromBundle(it).surveyModel
            val isFilled = ViewPagerFragment2Args.fromBundle(it).isFilled
            val fragmentList = arrayListOf<Fragment>(
                FillSurveyFragment(survey,isFilled),
                CommentFragment()
            )
            val adapter2 = SlidePageAdapter2(fragmentList, requireActivity().supportFragmentManager)
            val viewPager = binding.viewPager2
            viewPager.adapter = adapter2


        }


    }


    override fun onResume() {
        super.onResume()
        arguments?.let {
            val survey = ViewPagerFragment2Args.fromBundle(it).surveyModel
            val isFilled = ViewPagerFragment2Args.fromBundle(it).isFilled
            val fragmentList = arrayListOf<Fragment>(
                FillSurveyFragment(survey,isFilled),
                CommentFragment()
            )
            val adapter2 = SlidePageAdapter2(fragmentList, requireActivity().supportFragmentManager)
            val viewPager = binding.viewPager2
            viewPager.adapter = adapter2


        }


    }

    override fun onPause() {
        super.onPause()
        binding.viewPager2.adapter = null //I don't remember why I did this, I gues
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}
