package com.android.surveysaurus.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.android.surveysaurus.R
import com.android.surveysaurus.activity.MainActivity
import com.android.surveysaurus.databinding.FragmentSignUpBinding
import com.android.surveysaurus.model.SignUpModel
import com.android.surveysaurus.service.ApiService


class SignUpFragment : Fragment() {

    private  var _binding: FragmentSignUpBinding?=null
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
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root

        val countryAdapter: ArrayAdapter<*> = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.Country)
        )
        binding.spinnerCountry.setAdapter(countryAdapter)

        val cityAdapter: ArrayAdapter<*> = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.City)
        )
        binding.spinnerCity.setAdapter(cityAdapter)


     val spinnerGender: Spinner = view.findViewById(R.id.spinner_gender)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            view.context,
            R.array.Genders,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerGender.adapter = adapter
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.spinnerCity.setOnClickListener {
            binding.spinnerCity.showDropDown()
        }
        binding.spinnerCountry.setOnClickListener {
            binding.spinnerCountry.showDropDown()
        }
        binding.visiblePassword.setOnTouchListener(View.OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)// PRESSED
                    return@OnTouchListener true
                }// if you want to handle the touch event
                MotionEvent.ACTION_UP -> {
                    binding.editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)// RELEASED
                    return@OnTouchListener true
                }// if you want to handle the touch event
            }
            false
        })
binding.donTHave.setOnClickListener {
    val action=SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
    Navigation.findNavController(it).navigate(action)
}

        binding.button.setOnClickListener {
            val name=    binding.nameTextview.text
            val email=binding.editTextTextEmailAddress.text
            val password=binding.editTextTextPassword.text
            val gender =binding.spinnerGender.selectedItem.toString()
            val country =binding.spinnerCountry.text.toString()
            val city =binding.spinnerCity.text.toString()
            if (!name.isNullOrEmpty() && !email.isNullOrEmpty()&&!password.isNullOrEmpty()&&!gender.isNullOrEmpty()&&!country.isNullOrEmpty()&&!city.isNullOrEmpty())
            {
                if(!email.endsWith(".com") || !email.contains("@")) {
                    Toast.makeText(
                        view.context,
                        "Please enter a correct email", Toast.LENGTH_SHORT
                    ).show();
                }
                else if (password.length<8) {
                    Toast.makeText(
                        view.context,
                        "Your password needs to contain at least 8 letters", Toast.LENGTH_SHORT
                    ).show();
                }
                else {
                    try{
                        val apiService = ApiService()
                        var signUpModel: SignUpModel = SignUpModel(
                            name.toString(),
                            email.toString(),
                            password.toString(),
                            gender,
                            country,
                            city)

                        apiService.postSignUp(signUpModel){

                            if (it.toString() != null) {
                                Toast.makeText(view.context,
                                    "Succesful",Toast.LENGTH_SHORT).show();
                                val action=SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                                Navigation.findNavController(view).navigate(action)
                            } else {
                                Toast.makeText(view.context,
                                    "Fail",Toast.LENGTH_SHORT).show();
                                it
                            }
                        }

                    }
                    catch (e:Exception){
e.printStackTrace()
                    }

                }


            }
            else{
                Toast.makeText(view.context,
                        "Please fill in the starred fields",Toast.LENGTH_SHORT).show();

            }



        }
    }


}