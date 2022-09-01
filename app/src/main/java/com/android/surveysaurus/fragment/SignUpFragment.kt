package com.android.surveysaurus.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.android.surveysaurus.R
import com.android.surveysaurus.activity.MainActivity
import com.android.surveysaurus.databinding.FragmentSignUpBinding
import com.android.surveysaurus.model.CountryModel
import com.android.surveysaurus.model.SignUpModel
import com.android.surveysaurus.service.ApiService
import java.util.regex.Pattern


class SignUpFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private var isVisible: Boolean? =false
    private var isVisible2: Boolean? =false
    private var controlCity:ArrayList<String> = ArrayList()
    private var controlCountry :ArrayList<String> = ArrayList()
    private var controlGender : ArrayList<String> = ArrayList()
    private val mainActivity: MainActivity = MainActivity()

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


        try {
            val apiService = ApiService()

            apiService.getCountries {

                if (it.toString() != null) {
                    val countryAdapter: ArrayAdapter<*> = ArrayAdapter<String>(
                        view.context,
                        android.R.layout.simple_dropdown_item_1line,
                        it!!
                    )
                    binding.spinnerCountry.setAdapter(countryAdapter)
                   controlCountry.addAll(it)

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
        binding.spinnerGender.setAdapter(genderAdapter)
        controlGender.addAll( getResources().getStringArray(R.array.Genders))


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spinnerGender.setOnClickListener {
            binding.spinnerGender.showDropDown()
        }

        binding.spinnerCity.setOnClickListener {
            binding.spinnerCity.showDropDown()
        }
        binding.spinnerCountry.setOnClickListener {
            binding.spinnerCountry.showDropDown()
        }

        binding.spinnerCountry.setOnItemClickListener(this)
        binding.visiblePassword.setOnClickListener {

            if(isVisible==false){
                binding.editTextTextPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD// PRESSED
           isVisible=true
            }
           else{
                binding.editTextTextPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD// RELEASED
                isVisible=false
            }
        }
        binding.visiblePassword2.setOnClickListener {

            if(isVisible2==false){
                binding.editTextTextPassword2.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD// PRESSED
                isVisible2=true
            }
            else{
                binding.editTextTextPassword2.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD// RELEASED
                isVisible2=false
            }
        }



        binding.donTHave.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.button.setOnClickListener {
            val name = binding.nameTextview.text
            val email = binding.editTextTextEmailAddress.text
            val password = binding.editTextTextPassword.text.toString()
            val confirmPassword = binding.editTextTextPassword2.text.toString()
            val gender = binding.spinnerGender.text.toString()
            val country = binding.spinnerCountry.text.toString()
            val city = binding.spinnerCity.text.toString()
            if (!name.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty() && !gender.isNullOrEmpty() &&
                !country.isNullOrEmpty() && !city.isNullOrEmpty() && !confirmPassword.isNullOrEmpty()
            ) {
                if (!email.endsWith(".com") || !email.contains("@")) {
                    Toast.makeText(
                        view.context,
                        "Please enter a correct email", Toast.LENGTH_SHORT
                    ).show()

                }
                else if(!isValidInformation(binding.spinnerCountry.text.toString()
                        ,binding.spinnerCity.text.toString()
                        ,binding.spinnerGender.text.toString())){
                    Toast.makeText(
                        view.context,
                        "Please enter proper country, city and gender", Toast.LENGTH_SHORT
                    ).show();
                }
                else if (password != confirmPassword) {
                    Toast.makeText(
                        view.context,
                        "Your passwords needs to match", Toast.LENGTH_SHORT
                    ).show()
                }
                else if (password.length < 8) {
                    Toast.makeText(
                        view.context,
                        "Your password needs to contain at least 8 letters", Toast.LENGTH_SHORT
                    ).show()
                }
                else if(password.contains(" ")){
                    Toast.makeText(
                        view.context,
                        "Do not left spaces in your password", Toast.LENGTH_SHORT
                    ).show()
                }
                else if(!isValidPasswordFormat(password)){
                    Toast.makeText(
                        view.context,
                        "Enter your password in accordance with the password format ", Toast.LENGTH_SHORT
                    ).show()
                }

                else {
                    try {
                        val apiService = ApiService()
                        var signUpModel: SignUpModel = SignUpModel(
                            name.toString(),
                            email.toString(),
                            password.toString(),
                            gender,
                            country,
                            city
                        )

                        apiService.postSignUp(signUpModel) { it,str ->

                            if (it != null) {
                                Toast.makeText(
                                    view.context,
                                    "Succesful", Toast.LENGTH_SHORT
                                ).show();
                                val action =
                                    SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                                Navigation.findNavController(view).navigate(action)
                            } else {
                                Toast.makeText(
                                    view.context,
                                    str, Toast.LENGTH_LONG
                                ).show();

                            }
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }


            }
            else if(name.isNullOrEmpty()) {
                Toast.makeText(
                    view.context,
                    "Please enter your name", Toast.LENGTH_SHORT
                ).show()
            }
            else if(email.isNullOrEmpty()) {
                Toast.makeText(
                    view.context,
                    "Please enter your email", Toast.LENGTH_SHORT
                ).show()
            }
            else if(gender.isNullOrEmpty()) {
                Toast.makeText(
                    view.context,
                    "Please choose your gender", Toast.LENGTH_SHORT
                ).show()
            }
            else if(country.isNullOrEmpty()) {
            Toast.makeText(
                view.context,
                "Please enter your country", Toast.LENGTH_SHORT
            ).show()
        }
            else if(city.isNullOrEmpty()) {
                Toast.makeText(
                    view.context,
                    "Please enter your city", Toast.LENGTH_SHORT
                ).show()
            }
            else if(password.isNullOrEmpty()) {
                Toast.makeText(
                    view.context,
                    "Please enter your password", Toast.LENGTH_SHORT
                ).show()
            }
            else if(confirmPassword.isNullOrEmpty()) {
                Toast.makeText(
                    view.context,
                    "Please confirm your password", Toast.LENGTH_SHORT
                ).show()
            }
                else {
                Toast.makeText(
                    view.context,
                    "Please fill in the starred fields", Toast.LENGTH_SHORT
                ).show()
            }


        }
    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        controlCity.clear()
        binding.spinnerCity.setText("")
        try {
            val apiService = ApiService()
            var country:CountryModel= CountryModel(binding.spinnerCountry.text.toString())


            apiService.getCity(country){
                if (it.toString()!=null) {
                  val cityAdapter: ArrayAdapter<*> = ArrayAdapter<String>(
                        p1!!.context,
                        android.R.layout.simple_dropdown_item_1line,
                        it!!
                    )
                    binding.spinnerCity.setAdapter(cityAdapter)
                    controlCity.addAll(it)
                    println("succes")
                }
                else{
                    println("fail")
                }

            }} catch (e: Exception) {
            e.printStackTrace()
        }

    }
    fun isValidPasswordFormat(password: String): Boolean {
        val passwordREGEX = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[.,])" +
                //no white spaces
                ".{8,}" +
                "$");
        return passwordREGEX.matcher(password).matches()
    }

    fun isValidInformation(country: String,city :String , gender:String): Boolean {
        for(itemCountry in 0 until controlCountry.size){
            if (controlCountry.get(itemCountry).equals(country)){
                for(itemCity in 0 until controlCity.size){
                    if(controlCity.get(itemCity).equals(city)){
                        for(itemGender in 0 until controlGender.size){
                            if(controlGender.get(itemGender).equals(gender)){
                                return true
                            }
                        }
                    }
                }
            }
        }
        return false
    }

}