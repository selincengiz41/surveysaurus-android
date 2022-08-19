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
import com.android.surveysaurus.model.LoginModel
import com.android.surveysaurus.service.ApiService
import com.android.surveysaurus.singleton.LoginSingleton


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

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
                MotionEvent.ACTION_DOWN -> {
                    binding.editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)// PRESSED
                    println("bastım")
                    return@OnTouchListener true
                }// if you want to handle the touch event
                MotionEvent.ACTION_UP -> {
                    binding.editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)// RELEASED
                    println("bıraktım")
                    return@OnTouchListener true
                }// if you want to handle the touch event
            }
            false
        })


        binding.donTHave.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            Navigation.findNavController(it).navigate(action)
        }


        binding.button.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text
            val password = binding.editTextTextPassword.text
            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {

                if (!email.endsWith(".com") || !email.contains("@")) {
                    Toast.makeText(
                        view.context,
                        "Incorrect email or password", Toast.LENGTH_SHORT
                    ).show()
                } else if (password.length < 8) {
                    Toast.makeText(
                        view.context,
                        "Your password needs to contain at least 8 letters", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    try {

                        val apiService = ApiService()
                        var loginModel: LoginModel = LoginModel(
                            email.toString(), password.toString()
                        )
                        apiService.postLogin(loginModel) {
                            if (it != null) {
                                var text: String = it.string()
                                var tırnak = '"'

                                LoginSingleton.token = text.substringAfter("accessToken$tırnak")
                                    .substringBefore(tırnak + "data").substringAfter(tırnak)
                                    .substringBefore(tırnak)
                                println(LoginSingleton.token)
                                LoginSingleton.name = text.substringAfter("name$tırnak")
                                    .substringBefore(tırnak + "gender").substringAfter(tırnak)
                                    .substringBefore(tırnak)
                                LoginSingleton.gender = text.substringAfter("gender$tırnak")
                                    .substringBefore(tırnak + "email").substringAfter(tırnak)
                                    .substringBefore(tırnak)
                                LoginSingleton.country = text.substringAfter("country$tırnak")
                                    .substringBefore(tırnak + "message").substringAfter(tırnak)
                                    .substringBefore(tırnak)
                                LoginSingleton.city = text.substringAfter("city$tırnak")
                                    .substringBefore(tırnak + "country").substringAfter(tırnak)
                                    .substringBefore(tırnak)
                                LoginSingleton.email = text.substringAfter("email$tırnak")
                                    .substringBefore(tırnak + "city").substringAfter(tırnak)
                                    .substringBefore(tırnak)
                                Toast.makeText(
                                    view.context,
                                    "Succesfully logined " + LoginSingleton.name, Toast.LENGTH_LONG
                                ).show();
                                LoginSingleton.isLogin = true
                                (activity as MainActivity?)!!.MenuController()
                                val action =
                                    LoginFragmentDirections.actionLoginFragmentToCreateSurveyFragment()
                                Navigation.findNavController(view).navigate(action)

                            } else {
                                Toast.makeText(
                                    view.context,
                                    "Incorrect email or password", Toast.LENGTH_SHORT
                                ).show();
                            }

                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }


                }


            }
            else if(email.isNullOrEmpty()) {
                Toast.makeText(
                    view.context,
                    "Please fill the E-Mail field", Toast.LENGTH_SHORT
                ).show()
            }
            else if(password.isNullOrEmpty()) {
                Toast.makeText(
                    view.context,
                    "Please fill your password to continue", Toast.LENGTH_SHORT
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


}