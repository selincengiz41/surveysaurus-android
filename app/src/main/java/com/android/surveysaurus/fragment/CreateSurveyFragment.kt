package com.android.surveysaurus.fragment



import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.surveysaurus.activity.MainActivity

import com.android.surveysaurus.adapter.OptionAdapter
import com.android.surveysaurus.databinding.AddOptionLayBinding
import com.android.surveysaurus.databinding.FragmentCreateSurveyBinding


class CreateSurveyFragment : Fragment() {
    private  var _binding: FragmentCreateSurveyBinding?=null
    private val binding get() = _binding!!
    private var isThereAdditional :Boolean=false
    private var optionList:ArrayList<String> = ArrayList()
  private  val mainActivity:MainActivity=MainActivity()
private lateinit var optionAdapter: OptionAdapter

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
   binding.additionalOptions.layoutManager=LinearLayoutManager(view.context)
 optionAdapter= OptionAdapter()
        binding.additionalOptions.adapter=optionAdapter


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addOptionText.setOnClickListener{
 optionAdapter.recycleAdd(1)
            optionAdapter.notifyDataSetChanged()
            isThereAdditional=true

        }

        binding.createButton.setOnClickListener{

            val question=binding.addQuestion.text
            val description =binding.addDescription.text
if(!binding.addOption1.text.toString().isNullOrEmpty())
    optionList.add(binding.addOption1.text.toString())

            if(!binding.addOption2.text.toString().isNullOrEmpty())
                optionList.add(binding.addOption2.text.toString())

            if(!binding.addOption3.text.toString().isNullOrEmpty())
                optionList.add(binding.addOption3.text.toString())


            if(isThereAdditional) {
                val holder: OptionAdapter.OptionHolder
                holder =
                    OptionAdapter.OptionHolder(binding = AddOptionLayBinding.bind(binding.root))
                for (item in 0 until optionAdapter.itemCount) {
                    val title = binding.additionalOptions
                        .findViewHolderForAdapterPosition(item)
                        ?.itemView
                        ?.findViewById<TextView>(com.android.surveysaurus.R.id.add_optional)
                        ?.text

                    if(!title.toString().isNullOrEmpty())
                   optionList.add(title.toString())
                }
            }
            if(!question.isNullOrEmpty()&&optionList.size>=2){
                if( mainActivity.isLogin){

                }
                else{
                    val action=CreateSurveyFragmentDirections.actionCreateSurveyFragmentToLoginFragment()
                    Navigation.findNavController(it).navigate(action)
                }
            }
            else{
                Toast.makeText(view.context,
                            "Fill in the question field and add at least two options", Toast.LENGTH_SHORT).show();

            }





        }

    }


}