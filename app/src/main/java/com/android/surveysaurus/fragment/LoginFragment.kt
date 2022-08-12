package com.android.surveysaurus.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.android.surveysaurus.activity.MainActivity
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

        binding.visiblePassword.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->{
                    binding.editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)// PRESSED
                    println("bastım")
                    return@OnTouchListener true }// if you want to handle the touch event
                MotionEvent.ACTION_UP -> {
                    binding.editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)// RELEASED
                    println("bıraktım")
                    return@OnTouchListener true }// if you want to handle the touch event
            }
            false
        })


  binding.donTHave.setOnClickListener {
      val action=LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
      Navigation.findNavController(it).navigate(action)

  }
        binding.button.setOnClickListener{
            val email=binding.editTextTextEmailAddress.text
            val password=binding.editTextTextPassword.text
            if ( !email.isNullOrEmpty()&&!password.isNullOrEmpty()){

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
                        /*
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

                        }*/
                    }
                    catch(e:Exception){
                        e.printStackTrace()
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