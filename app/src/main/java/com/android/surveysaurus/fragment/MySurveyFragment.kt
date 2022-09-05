package com.android.surveysaurus.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.android.surveysaurus.adapter.SurveyAdapter
import com.android.surveysaurus.databinding.FragmentMySurveyBinding
import com.android.surveysaurus.model.CountryModel
import com.android.surveysaurus.model.IsFilledModel
import com.android.surveysaurus.model.ListedSurvey
import com.android.surveysaurus.model.Survey
import com.android.surveysaurus.service.ApiService


class MySurveyFragment : Fragment(), SurveyAdapter.Listener {
    private var _binding: FragmentMySurveyBinding? = null
    private val binding get() = _binding!!
    private lateinit var surveyAdapter: SurveyAdapter
    private var controlMySurvey:Boolean=false

    private lateinit var mySurveyModels: ArrayList<ListedSurvey>
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


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        try {

            val apiService = ApiService()


            apiService.getMySurvey {it,isThereSurvey->

                if (isThereSurvey!= null) {
                    Toast.makeText(
                        view.context,
                        "Succesful", Toast.LENGTH_SHORT
                    ).show();
                    if(it!=null){
                        if(controlMySurvey==false){
                            surveyAdapter = SurveyAdapter(it, this@MySurveyFragment)
                            binding.mySurveysRecycler.adapter = surveyAdapter
                            var options=ArrayList<String>()
                            var count=ArrayList<Int>()
                            var mySurveyModel:ListedSurvey = ListedSurvey( options,count,"","")


                            it.add(mySurveyModel)


                            surveyAdapter.notifyDataSetChanged()
                            controlMySurvey=true
                        }

                    }
                    else{
                        var list :ArrayList<ListedSurvey> = ArrayList()
                        surveyAdapter = SurveyAdapter(list, this@MySurveyFragment)
                        binding.mySurveysRecycler.adapter = surveyAdapter
                        var options=ArrayList<String>()
                        var count=ArrayList<Int>()
                        var mySurveyModel:ListedSurvey = ListedSurvey( options,count,"","")


                        list.add(mySurveyModel)


                        surveyAdapter.notifyDataSetChanged()
                    }



                } else {
                    Toast.makeText(
                        view.context,
                        "Fail", Toast.LENGTH_SHORT
                    ).show();

                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }






    }

    override fun onItemClick(mySurveyModel: ListedSurvey) {

        try {
            val apiService = ApiService()
            val isfilled: IsFilledModel =IsFilledModel(mySurveyModel.title)


            apiService.isFilled(isfilled){
                if (it.toString()!=null) {
                    val action =
                        MySurveyFragmentDirections.actionMySurveyFragmentToFillSurveyFragment(mySurveyModel,it)
                    Navigation.findNavController(binding.root).navigate(action)
                    println("succes")
                }
                else{
                    val action =
                        MySurveyFragmentDirections.actionMySurveyFragmentToFillSurveyFragment(mySurveyModel)
                    Navigation.findNavController(binding.root).navigate(action)
                    println("fail")
                }

            }} catch (e: Exception) {
            e.printStackTrace()
        }


    }

    override fun onItemClick2() {

    }


}