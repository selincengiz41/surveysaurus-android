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
        options.add("Golden Fish")
        options.add("Dolphin")
        options.add("Rabbit")
        var surveyModel:SurveyModel= SurveyModel("Which animals do you like most?","Yok",
           options
        )

        var options2:ArrayList<String> =ArrayList()
        options2.add("Books and articles")
        options2.add("YouTube tutorial videos")
        options2.add("Online or face to face courses")
        options2.add("Experience with little investment")
        var surveyModel2:SurveyModel= SurveyModel("What resources have you used or are you using for your training?","Yok",
            options2
        )

        var options3:ArrayList<String> =ArrayList()
        options3.add("Stock market")
        options3.add("Foreign exchange")
        options3.add("Commodity")
        options3.add("Bond market")
        options3.add("Cryptocurrency market")
        var surveyModel3:SurveyModel= SurveyModel("In which financial markets do you operate?","Yok",
            options3
        )
        surveyAdapter.recycleAdd(surveyModel)
        surveyAdapter.recycleAdd(surveyModel2)
        surveyAdapter.recycleAdd(surveyModel3)
        surveyAdapter.recycleAdd(surveyModel)
        surveyAdapter.recycleAdd(surveyModel2)
        surveyAdapter.recycleAdd(surveyModel3)
        surveyAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(surveyModel: SurveyModel) {
        val action=ViewPagerFragmentDirections.actionViewPagerFragmentToFillSurveyFragment(surveyModel)
        Navigation.findNavController(binding.root).navigate(action)
    }


}