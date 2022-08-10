package com.selincengiz.surveysaurus.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.selincengiz.surveysaurus.R
import com.selincengiz.surveysaurus.databinding.FragmentAddOptionBinding
import com.selincengiz.surveysaurus.databinding.FragmentCreateSurveyBinding


class CreateSurveyFragment : Fragment() {
    private  var _binding: FragmentCreateSurveyBinding?=null
    private val binding get() = _binding!!
    private  var _bindingOption: FragmentAddOptionBinding?=null
    private val bindingOption get() = _bindingOption!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateSurveyBinding.inflate(inflater, container, false)
        val view = binding.root
        _bindingOption = FragmentAddOptionBinding.inflate(inflater, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addOptionText.setOnClickListener{
            val constraintLayout: ConstraintLayout =  bindingOption.constraintOptional
            val constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)
            constraintSet.connect(
                binding.addedLayout.id,
                ConstraintSet.RIGHT,
                binding.addedLayout.id,
                ConstraintSet.LEFT,
                0
            )
            constraintSet.connect(
                binding.option3.id,
                ConstraintSet.TOP,
                binding.addOptionText.id,
                ConstraintSet.BOTTOM,
                0
            )
            constraintSet.applyTo(constraintLayout)

           binding.addedLayout.addView(constraintLayout)

        }
    }


}