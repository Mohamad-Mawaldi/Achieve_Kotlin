package com.example.achievekotlin.Models

data class Task (var title:String, var body:String, var date: String, var id: String, var done: Boolean = false , var isExpanded: Boolean = false)