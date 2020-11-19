package com.example.achievekotlin.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.achievekotlin.R
import com.example.achievekotlin.Interfaces.iFragmentListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_sign_in.view.*


class SignInFragment : Fragment() {

    private lateinit var fragmentListener: iFragmentListener

    private var auth: FirebaseAuth = Firebase.auth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        fragmentListener = activity as iFragmentListener


        view.sign_in.setOnClickListener {

            when {
                view.email.text.isNullOrEmpty() -> Toast.makeText(
                    activity,
                    "Please fill in your Email",
                    Toast.LENGTH_SHORT
                ).show()
                view.password.text.isNullOrEmpty() -> Toast.makeText(
                    activity,
                    "Please fill in your Password",
                    Toast.LENGTH_SHORT
                ).show()
                else -> auth.signInWithEmailAndPassword(
                    view.email.text.toString(),
                    view.password.text.toString()
                )

                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) fragmentListener.isAuthenticated()
                        else Toast.makeText(activity, task.exception?.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
            }

        }

        view.no_account.setOnClickListener {
            fragmentListener.setFragmentToSignUp()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}




