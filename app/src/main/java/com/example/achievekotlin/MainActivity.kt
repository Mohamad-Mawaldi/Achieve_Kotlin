package com.example.achievekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var textView: TextView

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
             //   moveToFragment(HomeFragment())
                textView.setText("Home")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_add -> {
             //   moveToFragment(AddFragment())
                textView.setText("Add")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_graph -> {
                //   moveToFragment(GraphFragment())
                textView.setText("graph")
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        textView = findViewById(R.id.text)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)


    }
}