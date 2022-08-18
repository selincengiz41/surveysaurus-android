package com.android.surveysaurus.fragment

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.android.surveysaurus.R
import com.android.surveysaurus.databinding.FragmentHomeBinding
import com.android.surveysaurus.databinding.FragmentMySurveyBinding
import com.android.surveysaurus.databinding.FragmentUserInfoBinding
import com.android.surveysaurus.singleton.LoginSingleton


class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        val countryAdapter: ArrayAdapter<*> = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_dropdown_item_1line,
            getResources().getStringArray(R.array.Country)
        )
        binding.spinnerCountry4.setAdapter(countryAdapter)

        val cityAdapter: ArrayAdapter<*> = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.City)
        )
        binding.spinnerCity4.setAdapter(cityAdapter)

        val genderAdapter: ArrayAdapter<*> = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_dropdown_item_1line,
            getResources().getStringArray(R.array.Genders)
        )
        binding.spinnerGender3.setAdapter(genderAdapter)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spinnerCity4.setOnClickListener {
            binding.spinnerCity4.showDropDown()
        }
        binding.spinnerCountry4.setOnClickListener {
            binding.spinnerCountry4.showDropDown()
        }
        binding.spinnerGender3.setOnClickListener {
            binding.spinnerGender3.showDropDown()
        }
        binding.nameTextview3.setText(LoginSingleton.name, TextView.BufferType.EDITABLE)
        binding.editTextTextEmailAddress3.setText(
            LoginSingleton.email,
            TextView.BufferType.EDITABLE
        )
        binding.spinnerCity4.setText(LoginSingleton.city)
        binding.spinnerCountry4.setText(LoginSingleton.country)
        binding.spinnerGender3.setText(LoginSingleton.gender)


    }


}