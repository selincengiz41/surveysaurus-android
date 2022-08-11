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
import com.android.surveysaurus.model.LoginModel
import com.android.surveysaurus.service.ApiService
import com.android.surveysaurus.singleton.LoginSingleton


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
                val apiService = ApiService()
                var loginModel:LoginModel= LoginModel(email.toString()
                ,password.toString())
                apiService.postLogin(loginModel){
                    if (it != null) {
                        Toast.makeText(view.context,
                            "Succesfully logined "+it.name,Toast.LENGTH_SHORT).show();
                        LoginSingleton.isLogin=true
                        mainActivity.MenuController()

                    } else {
                        Toast.makeText(view.context,
                            "Fail",Toast.LENGTH_SHORT).show();
                    }
                }

            }
            else{
                Toast.makeText(view.context,
                    "Please fill in the starred fields", Toast.LENGTH_SHORT).show();

            }

        }
    }


}