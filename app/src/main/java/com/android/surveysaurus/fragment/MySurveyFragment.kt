package com.android.surveysaurus.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.android.surveysaurus.activity.MainActivity
import com.android.surveysaurus.adapter.SurveyAdapter
import com.android.surveysaurus.databinding.FragmentMySurveyBinding
import com.android.surveysaurus.databinding.FragmentSurveysBinding
import com.android.surveysaurus.model.SurveyModel


class MySurveyFragment : Fragment(), SurveyAdapter.Listener {
    private var _binding: FragmentMySurveyBinding? = null
    private val binding get() = _binding!!
    private lateinit var surveyAdapter: SurveyAdapter
    private var surveyModel: ArrayList<SurveyModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMySurveyBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.mySurveysRecycler.layoutManager = GridLayoutManager(view.context, 2)
        surveyAdapter = SurveyAdapter(surveyModel, this@MySurveyFragment)
        binding.mySurveysRecycler.adapter = surveyAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //var options = arrayOf<String>("Cat", "Dog", "Golden Fish", "Dolphin", "Rabbit")

        var options=ArrayList<String>()
        options.add("Cat")
        options.add("Dog")
        options.add("Golden Fish")
        options.add("Dolphin")
        options.add("Rabbit")
        var surveyModel1: SurveyModel = SurveyModel(
            "Which animals do you like most?", "Yok",
            options
        )

       /* var options2 = arrayOf<String>(
            "Books and articles",
            "YouTube tutorial videos",
            "Online or face to face courses",
            "Experience with little investment"
        )*/
        var options2=ArrayList<String>()
        options2.add("Books and articles")
        options2.add("YouTube tutorial videos")
        options2.add("Online or face to face courses")
        options2.add("Experience with little investment")

        var surveyModel2: SurveyModel = SurveyModel(
            "What resources have you used or are you using for your training?", "Yok",
            options2
        )

        /*var options3 = arrayOf<String>(
            "Stock market", "Foreign exchange", "Commodity", "Bond market", "Cryptocurrency market"
        )*/
        var options3=ArrayList<String>()
        options3.add("Stock market")
        options3.add("Foreign exchange")
        options3.add("Commodity")
        options3.add("Bond market")
        options3.add("Cryptocurrency market")
        var surveyModel3: SurveyModel = SurveyModel(
            "In which financial markets do you operate?", "Yok",
            options3
        )
        var options4=ArrayList<String>()
      //  var options4 = arrayOf<String>()
        var surveyModel4: SurveyModel = SurveyModel("", "", options4)

        surveyModel.add(surveyModel1)
        surveyModel.add(surveyModel2)
        surveyModel.add(surveyModel3)
        surveyModel.add(surveyModel1)
        surveyModel.add(surveyModel2)
        surveyModel.add(surveyModel4)


        surveyAdapter.notifyDataSetChanged()

    }

    override fun onItemClick(surveyModel: SurveyModel) {
        val action =
            MySurveyFragmentDirections.actionMySurveyFragmentToFillSurveyFragment(surveyModel)
        Navigation.findNavController(binding.root).navigate(action)
    }


}