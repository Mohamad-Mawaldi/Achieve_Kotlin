package com.example.achievekotlin

interface iFragmentListener {

    // SignUp fragment
    fun setFragmentToSignUp()

    // SignIn fragment
    fun setFragmentToSignIn()

    // User is authenticated -> home fragment
    fun isAuthenticated()

    // Logout -> SignIn fragment
    fun logOut()

}