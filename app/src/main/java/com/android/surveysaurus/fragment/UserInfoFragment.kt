package com.android.surveysaurus.fragment

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.surveysaurus.R
import com.android.surveysaurus.adapter.SurveyAdapter
import com.android.surveysaurus.databinding.FragmentHomeBinding
import com.android.surveysaurus.databinding.FragmentMySurveyBinding
import com.android.surveysaurus.databinding.FragmentUserInfoBinding
import com.android.surveysaurus.model.CountryModel
import com.android.surveysaurus.model.PasswordModel
import com.android.surveysaurus.model.UpdateModel
import com.android.surveysaurus.service.ApiService
import com.android.surveysaurus.singleton.LoginSingleton


class UserInfoFragment : Fragment(), AdapterView.OnItemClickListener {
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

        try {
            val apiService = ApiService()

            apiService.getCountries {

                if (it.toString() != null) {
                    val countryAdapter: ArrayAdapter<*> = ArrayAdapter<String>(
                        view.context,
                        android.R.layout.simple_dropdown_item_1line,
                        it!!
                    )
                    binding.spinnerCountry4.setAdapter(countryAdapter)


                } else {


                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


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
        binding.visiblePassword8.setOnTouchListener(View.OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.editTextTextPassword6.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD// PRESSED
                    return@OnTouchListener true
                }// if you want to handle the touch event
                MotionEvent.ACTION_UP -> {
                    binding.editTextTextPassword6.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD// RELEASED
                    return@OnTouchListener true
                }// if you want to handle the touch event
            }
            false
        })
        binding.visiblePassword7.setOnTouchListener(View.OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.editTextTextPassword7.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD// PRESSED
                    return@OnTouchListener true
                }// if you want to handle the touch event
                MotionEvent.ACTION_UP -> {
                    binding.editTextTextPassword7.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD// RELEASED
                    return@OnTouchListener true
                }// if you want to handle the touch event
            }
            false
        })
        binding.spinnerCountry4.setOnItemClickListener(this)
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

        binding.button2.setOnClickListener {
            try {

                val apiService = ApiService()
                val updateModel: UpdateModel = UpdateModel(
                    binding.nameTextview3.text.toString(),
                    binding.editTextTextEmailAddress3.text.toString(),
                    binding.spinnerCountry4.text.toString(),
                    binding.spinnerCity4.text.toString()
                )

                apiService.updateUser(updateModel)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (!binding.editTextTextPassword6.text.toString()
                    .isNullOrEmpty() && !binding.editTextTextPassword7.text.toString()
                    .isNullOrEmpty()
            ) {
                try {

                    val apiService = ApiService()
                    val passwordModel: PasswordModel = PasswordModel(
                        binding.editTextTextPassword6.text.toString(),
                        binding.editTextTextPassword7.text.toString()

                    )

                    apiService.updatePassword(passwordModel)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }


        }


    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        try {
            val apiService = ApiService()
            var country: CountryModel = CountryModel(binding.spinnerCountry4.text.toString())


            apiService.getCity(country) {
                if (it.toString() != null) {
                    val cityAdapter: ArrayAdapter<*> = ArrayAdapter<String>(
                        p1!!.context,
                        android.R.layout.simple_dropdown_item_1line,
                        it!!
                    )
                    binding.spinnerCity4.setAdapter(cityAdapter)
                    println("succes")
                } else {
                    println("fail")
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}