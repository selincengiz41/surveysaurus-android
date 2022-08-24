package com.android.surveysaurus.fragment


import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.surveysaurus.R
import com.android.surveysaurus.adapter.OptionAdapter
import com.android.surveysaurus.databinding.AddOptionLayBinding
import com.android.surveysaurus.databinding.FragmentCreateSurveyBinding
import com.android.surveysaurus.model.SurveyModel
import com.android.surveysaurus.service.ApiService
import com.android.surveysaurus.singleton.LoginSingleton


class CreateSurveyFragment : Fragment(), OptionAdapter.Listener {
    private var _binding: FragmentCreateSurveyBinding? = null
    private val binding get() = _binding!!
    private var isThereAdditional: Boolean = false
    private var optionList: ArrayList<String> = ArrayList()
    private lateinit var optionAdapter: OptionAdapter
    private val optionSize: ArrayList<Int> = ArrayList()


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
        binding.additionalOptions.layoutManager = LinearLayoutManager(view.context)
        optionAdapter = OptionAdapter(optionSize, this@CreateSurveyFragment)
        binding.additionalOptions.adapter = optionAdapter


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addOptionText.setOnClickListener {
            optionSize.add(1)
            optionAdapter.notifyDataSetChanged()
            isThereAdditional = true

        }

        binding.createButton.setOnClickListener {

            val question = binding.addQuestion.text.toString()
            val title = binding.addTitle.text.toString()
            if (!binding.addOption1.text.toString().isNullOrEmpty())
                optionList.add(binding.addOption1.text.toString())

            if (!binding.addOption2.text.toString().isNullOrEmpty())
                optionList.add(binding.addOption2.text.toString())






            if (isThereAdditional) {
                val holder: OptionAdapter.OptionHolder
                holder =
                    OptionAdapter.OptionHolder(binding = AddOptionLayBinding.bind(binding.root))
                for (item in 0 until optionAdapter.itemCount) {
                    val addition = binding.additionalOptions
                        .findViewHolderForAdapterPosition(item)
                        ?.itemView
                        ?.findViewById<TextView>(com.android.surveysaurus.R.id.add_optional)
                        ?.text

                    if (!addition.toString().isNullOrEmpty())
                        optionList.add(addition.toString())
                }
            }
            if (!question.isNullOrEmpty() && optionList.size >= 2 && !title.isNullOrEmpty()) {
                if (LoginSingleton.isLogin) {

                    try {

                        val apiService = ApiService()

                        var surveyModel: SurveyModel = SurveyModel(question, title, optionList)
                        apiService.postCreateSurvey(surveyModel) {

                            if (it.toString() != null) {
                                Toast.makeText(
                                    view.context,
                                    "Succesful", Toast.LENGTH_SHORT
                                ).show();

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
                } else {
                    val action =
                        CreateSurveyFragmentDirections.actionCreateSurveyFragmentToLoginFragment()
                    Navigation.findNavController(it).navigate(action)
                }
            } else {
                Toast.makeText(
                    view.context,
                    "Fill in the question field and add at least two options", Toast.LENGTH_SHORT
                ).show();

            }


        }

    }

    override fun onItemClick(optionList: ArrayList<Int>) {
        if (optionList.size == 0)
            isThereAdditional = false
    }
/*
     override fun onPause() {
        super.onPause()


        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()


        myEdit.putString("question",binding.addQuestion.text.toString())
         myEdit.putString("title",binding.addTitle.text.toString())
         myEdit.putString("option1",binding.addOption1.text.toString())
         myEdit.putString("option2",binding.addOption2.text.toString())
         myEdit.putString("option3",binding.addOption3.text.toString())
        myEdit.apply()
    }

     override fun onResume() {
        super.onResume()

        // Fetching the stored data
        // from the SharedPreference
        val sh: SharedPreferences = requireActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE)
         binding.addQuestion.setText(sh.getString("question", ""))
         binding.addTitle.setText(sh.getString("title", ""))
         binding.addOption1.setText(sh.getString("option1", ""))
         binding.addOption2.setText(sh.getString("option2", ""))
         binding.addOption3.setText(sh.getString("option3", ""))
         // Setting the fetched data
        // in the EditTexts

    }


*/

}