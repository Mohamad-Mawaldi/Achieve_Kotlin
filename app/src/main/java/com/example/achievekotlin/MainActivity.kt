package com.example.achievekotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.achievekotlin.Fragments.HomeFragment
import com.example.achievekotlin.Fragments.SignInFragment
import com.example.achievekotlin.Fragments.SignUpFragment
import com.example.achievekotlin.Interfaces.iFragmentListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(),
    iFragmentListener {

    private var auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (auth.currentUser != null) {

            isAuthenticated()

        } else setFragmentToSignIn()

    }

    override fun setFragmentToSignUp() {
        moveToFragment(SignUpFragment())

    }

    override fun setFragmentToSignIn() {
        moveToFragment(SignInFragment())
    }

    override fun isAuthenticated() {
        moveToFragment(HomeFragment())
    }

    override fun logOut() {
        moveToFragment(SignInFragment())
    }

    private fun moveToFragment(fragment: Fragment)
     {
         val fragmentTrans = supportFragmentManager.beginTransaction()
         fragmentTrans.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
         fragmentTrans.disallowAddToBackStack()
         fragmentTrans.replace(R.id.fragment_container, fragment)
         fragmentTrans.commit()
     }

}