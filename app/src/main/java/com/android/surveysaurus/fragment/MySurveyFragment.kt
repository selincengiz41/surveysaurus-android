package com.android.surveysaurus.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.android.surveysaurus.activity.MainActivity
import com.android.surveysaurus.databinding.FragmentMySurveyBinding
import com.android.surveysaurus.databinding.FragmentSurveysBinding
import com.android.surveysaurus.model.SurveyModel


class MySurveyFragment : Fragment(){
    private  var _binding: FragmentMySurveyBinding?=null
    private val binding get() = _binding!!
    private  val mainActivity: MainActivity = MainActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //_binding = FragmentMySurveyBinding.inflate(inflater, container, false)
        _binding= FragmentMySurveyBinding.inflate(inflater,container,false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.button4.setOnClickListener{
            val action = MySurveyFragmentDirections.actionMySurveyFragmentToCreateSurveyFragment()
            Navigation.findNavController(it).navigate(action)

        binding.textView8.setOnClickListener {

        }

        }
    }



    fun onItemClick(surveyModel: SurveyModel) {

    }

}