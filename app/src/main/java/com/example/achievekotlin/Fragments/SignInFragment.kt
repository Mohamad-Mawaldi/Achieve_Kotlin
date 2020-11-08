package com.example.achievekotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast

import com.example.achievekotlin.R
import com.example.achievekotlin.iFragmentListener

import kotlinx.android.synthetic.main.fragment_sign_in.view.*


class SignInFragment : Fragment() {

    private lateinit var fragmentListener: iFragmentListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        fragmentListener = activity as iFragmentListener


        view.sign_in.setOnClickListener {
            Toast.makeText(activity, "sign_in hellooooooo" + view.email.text, Toast.LENGTH_SHORT).show()
            fragmentListener.isAuthenticated()

        }

        view.no_account.setOnClickListener {
            Toast.makeText(activity, "sign_up hellooooooo" + view.password.text, Toast.LENGTH_SHORT).show()
            fragmentListener.setFragmentToSignUp()

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}




