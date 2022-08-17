package com.android.surveysaurus.fragment

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.android.surveysaurus.R
import com.android.surveysaurus.databinding.FragmentFillSurveyBinding
import com.android.surveysaurus.singleton.LoginSingleton
import java.time.Duration


class FillSurveyFragment : Fragment() {
    private var _binding: FragmentFillSurveyBinding? = null
    private val binding get() = _binding!!
    private var index: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFillSurveyBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var optionList: ArrayList<TextView> = ArrayList()


        arguments?.let {
            val survey = FillSurveyFragmentArgs.fromBundle(it).surveyModel
            binding.addQuestionFill.text = survey.question
            binding.addTitleFill.text = survey.title
            for (item in 0 until survey.choice.size) {
                val option1: TextView = TextView(view.context)
                option1.id = View.generateViewId()
                option1.text = survey.choice.get(item)
                option1.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.vector3, 0, 0, 0)
                option1.setPaddingRelative(20, 0, 0, 0)
                option1.setBackgroundResource(R.drawable.question)
                option1.gravity = Gravity.CENTER
                option1.textSize = 15f
                binding.root.addView(option1)
                optionList.add(option1)
                val params = option1.layoutParams as ConstraintLayout.LayoutParams
                if (item == 0)
                    params.topToBottom = binding.optionsFill.id
                else
                    params.topToBottom = optionList.get(item - 1).id
                params.startToStart = binding.fillLayout.id
                params.endToEnd = binding.fillLayout.id
                params.topMargin = 20
                option1.requestLayout()
            }
        }

        val params = binding.doneLayout.layoutParams as ConstraintLayout.LayoutParams
        params.topToBottom = optionList.get(optionList.lastIndex).id
        params.topMargin = 40
        binding.doneLayout.requestLayout()

        for (item in 0 until optionList.size) {
            optionList.get(item).setOnClickListener {

                optionList.get(item).setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.selected_option,
                    0,
                    0,
                    0
                )
                for (other in 0 until optionList.size) {
                    if (other == item) {
                        index = other
                    } else {
                        optionList.get(other).setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.vector3,
                            0,
                            0,
                            0
                        )
                    }
                }
            }
        }

        binding.doneButton.setOnClickListener {
            if (index != null) {
                if (LoginSingleton.isLogin) {
                    println(optionList.get(index!!).text)
                } else {
                    val action =
                        FillSurveyFragmentDirections.actionFillSurveyFragmentToLoginFragment()
                    Navigation.findNavController(it).navigate(action)
                }

            } else {
                Toast.makeText(view.context, "Please select one of options", Toast.LENGTH_SHORT)
                    .show()
            }


        }


    }


}


