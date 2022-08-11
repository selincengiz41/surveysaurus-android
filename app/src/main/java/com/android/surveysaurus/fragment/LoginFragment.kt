package com.android.surveysaurus.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.android.surveysaurus.R
import com.android.surveysaurus.activity.MainActivity
import com.android.surveysaurus.databinding.FragmentHomeBinding
import com.android.surveysaurus.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private  var _binding: FragmentLoginBinding?=null
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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

  binding.donTHave.setOnClickListener {
      val action=LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
      Navigation.findNavController(it).navigate(action)

  }
        binding.button.setOnClickListener{
            val email=binding.editTextTextEmailAddress.text
            val password=binding.editTextTextPassword.text
            if ( !email.isNullOrEmpty()&&!password.isNullOrEmpty()){


                println(email)
                println(password)

            }
            else{
                Toast.makeText(view.context,
                    "Please fill in the starred fields", Toast.LENGTH_SHORT).show();

            }

        }
    }


}