package com.android.surveysaurus.fragment

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.Navigation
import com.android.surveysaurus.R
import com.android.surveysaurus.activity.MainActivity
import com.android.surveysaurus.databinding.FragmentHomeBinding
import com.android.surveysaurus.databinding.FragmentLoginBinding
import com.android.surveysaurus.databinding.FragmentSignUpBinding
import com.android.surveysaurus.model.SignUpModel
import com.android.surveysaurus.service.ApiService
import com.android.surveysaurus.singleton.LoginSingleton


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

        val spinnerCountry: Spinner = view.findViewById(R.id.spinner_country)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            view.context,
            R.array.Country,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerCountry.adapter = adapter
        }

        val spinnerCity: Spinner = view.findViewById(R.id.spinner_city)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            view.context,
            R.array.City,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerCity.adapter = adapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

binding.donTHave.setOnClickListener {
    val action=SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
    Navigation.findNavController(it).navigate(action)
}

        binding.button.setOnClickListener {
            val name=    binding.nameTextview.text
            val email=binding.editTextTextEmailAddress.text
            val password=binding.editTextTextPassword.text
            val gender =binding.spinnerGender.selectedItem.toString()
            val country =binding.spinnerCountry.selectedItem.toString()
            val city =binding.spinnerCity.selectedItem.toString()
            if (!name.isNullOrEmpty() && !email.isNullOrEmpty()&&!password.isNullOrEmpty()&&!gender.isNullOrEmpty()&&!country.isNullOrEmpty()&&!city.isNullOrEmpty()){
                val apiService = ApiService()
              var signUpModel:SignUpModel= SignUpModel(
                name.toString(),
                email.toString(),
               password.toString(),
                gender,
                country,
                city)

                apiService.postSignUp(signUpModel){
                    if (it != null) {
                        Toast.makeText(view.context,
                            "Succesful",Toast.LENGTH_SHORT).show();
                        val action=SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                        Navigation.findNavController(view).navigate(action)
                    } else {
                        Toast.makeText(view.context,
                            "Fail",Toast.LENGTH_SHORT).show();
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