package com.android.surveysaurus.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.appcompat.widget.ActionMenuView

import androidx.fragment.app.*
import androidx.navigation.findNavController
import com.android.surveysaurus.R

import com.android.surveysaurus.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
private  lateinit var profileActionMenuView: ActionMenuView
    private  lateinit var barActionMenuView: ActionMenuView
    private var isLogin:Boolean=false

private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        profileActionMenuView=findViewById(R.id.expanded_profile)
        val profileMenu=profileActionMenuView.menu
        barActionMenuView=findViewById(R.id.expanded_bar)
        val barMenu=barActionMenuView.menu



if (isLogin){

    menuInflater.inflate(R.menu.logined_profile_menu,profileMenu)
    profileActionMenuView.setOnMenuItemClickListener {
    val item=it.itemId
    when (item){
    R.id.user_info ->{  println("user info clicked "); true}
    R.id.my_surveys -> {  println("my surveys clicked "); true}
    R.id.log_out -> {  println("log out clicked "); true}

        else -> {  println("nothin "); true}
}

}
    menuInflater.inflate(R.menu.logined_bar_menu,barMenu)
    barActionMenuView.setOnMenuItemClickListener {

        val item=it.itemId
        when (item){
            R.id.user_bar ->{ println("clicked selin cengiz");true}


            else -> {  println("nothin "); true}
        }


    }
}
        else{
    menuInflater.inflate(R.menu.profile_menu,profileMenu)
    profileActionMenuView.setOnMenuItemClickListener {
        val item=it.itemId
   when(item){
       R.id.dropdown_menu -> {
           println("logine yönlendiriliyor");true}
       else->{
           println("nothin");true}
   }

    }
    menuInflater.inflate(R.menu.bar_menu,barMenu)
    barActionMenuView.setOnMenuItemClickListener {
        val id  =it.itemId
        when(id){
            R.id.home_bar ->{
                binding.fragmentContainerView.findNavController().navigate(R.id.viewPagerFragment);true}
            R.id.log_in -> {  binding.fragmentContainerView.findNavController().navigate(R.id.loginFragment);true}
            R.id.sign_up -> {   binding.fragmentContainerView.findNavController().navigate(R.id.signUpFragment);true}

            else -> {  println("nothin ");true}}
        }

    }

    }








        }




