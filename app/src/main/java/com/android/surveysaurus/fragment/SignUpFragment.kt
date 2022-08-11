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

        val spinner: Spinner = view.findViewById(R.id.spinner_gender)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            view.context,
            R.array.Genders,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
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
            if (!name.isNullOrEmpty() && !email.isNullOrEmpty()&&!password.isNullOrEmpty()&&!gender.isNullOrEmpty()){

                println(name)
                println(email)
                println(password)
                println(gender)
            }
            else{
                Toast.makeText(view.context,
                        "Please fill in the starred fields",Toast.LENGTH_SHORT).show();

            }



        }
    }


}