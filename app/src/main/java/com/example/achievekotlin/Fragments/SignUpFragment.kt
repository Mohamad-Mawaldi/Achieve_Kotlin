package com.example.achievekotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.achievekotlin.Models.UserInfo
import com.example.achievekotlin.R
import com.example.achievekotlin.Interfaces.iFragmentListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.email
import kotlinx.android.synthetic.main.fragment_sign_up.view.password


class SignUpFragment : Fragment() {


    private lateinit var fragmentListener: iFragmentListener
    private var auth: FirebaseAuth = Firebase.auth
    private var database: DatabaseReference = Firebase.database.reference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        fragmentListener = activity as iFragmentListener


        view.sign_up.setOnClickListener {

            when {
                view.email.text.isNullOrEmpty() -> Toast.makeText(
                    activity,
                    "Please fill in your Email",
                    Toast.LENGTH_SHORT
                ).show()
                view.username.text.isNullOrEmpty() -> Toast.makeText(
                    activity,
                    "Please fill in your Name",
                    Toast.LENGTH_SHORT
                ).show()
                view.password.text.isNullOrEmpty() -> Toast.makeText(
                    activity,
                    "Please fill in your Password",
                    Toast.LENGTH_SHORT
                ).show()

                else -> auth.createUserWithEmailAndPassword(
                    view.email.text.toString(),
                    view.password.text.toString()
                )
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            database.child("users").child(auth.uid.toString()).child("userInfo")
                                .setValue(
                                    UserInfo(
                                        view.username.text.toString(),
                                        view.email.text.toString()
                                    )
                                ).addOnCompleteListener {

                                    fragmentListener.isAuthenticated()

                                }

                        } else Toast.makeText(
                            activity,
                            task.exception?.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()


                    }

            }


        }

        view.have_account.setOnClickListener {
            fragmentListener.setFragmentToSignIn()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}