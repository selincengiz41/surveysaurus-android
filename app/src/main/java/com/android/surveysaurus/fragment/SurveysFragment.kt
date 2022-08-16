package com.android.surveysaurus.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.surveysaurus.R
import com.android.surveysaurus.activity.MainActivity
import com.android.surveysaurus.adapter.OptionAdapter
import com.android.surveysaurus.adapter.SurveyAdapter
import com.android.surveysaurus.databinding.FragmentCreateSurveyBinding
import com.android.surveysaurus.databinding.FragmentSurveysBinding
import com.android.surveysaurus.model.SurveyModel
import java.lang.reflect.Array

//
class SurveysFragment : Fragment(), SurveyAdapter.Listener {
    private  var _binding: FragmentSurveysBinding?=null
    private val binding get() = _binding!!
    private lateinit var surveyAdapter: SurveyAdapter
    private  val mainActivity: MainActivity = MainActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSurveysBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.surveysRecycler.layoutManager= GridLayoutManager(view.context,2)
        surveyAdapter= SurveyAdapter(this@SurveysFragment)
        binding.surveysRecycler.adapter=surveyAdapter


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       var options:ArrayList<String> =ArrayList()
        options.add("Cat")
        options.add("Dog")
        options.add("Fish")
        var surveyModel:SurveyModel= SurveyModel("Which animals do you like most?","Yok",
           options
        )
        surveyAdapter.recycleAdd(surveyModel)
        surveyAdapter.recycleAdd(surveyModel)
        surveyAdapter.recycleAdd(surveyModel)
        surveyAdapter.recycleAdd(surveyModel)
        surveyAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(surveyModel: SurveyModel) {
        val action=ViewPagerFragmentDirections.actionViewPagerFragmentToFillSurveyFragment(surveyModel)
        Navigation.findNavController(binding.root).navigate(action)
    }


}