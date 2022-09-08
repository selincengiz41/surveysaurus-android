package com.android.surveysaurus.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.surveysaurus.R
import com.android.surveysaurus.databinding.FragmentCommentBinding
import com.android.surveysaurus.databinding.FragmentCreateSurveyBinding
import com.android.surveysaurus.databinding.FragmentHomeBinding


class CommentFragment : Fragment() {
    private  var _binding: FragmentCommentBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCommentBinding.inflate(inflater, container, false)

        val view = binding.root
        return  view
    }


}