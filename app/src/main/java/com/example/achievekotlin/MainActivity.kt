package com.example.achievekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.achievekotlin.Fragments.HomeFragment


class MainActivity : AppCompatActivity() {


    /*private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {

                moveToFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_add -> {

               moveToFragment(AddFragment())
               return@OnNavigationItemSelectedListener true
            }
            R.id.nav_graph -> {
                moveToFragment(GraphFragment())
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)

        /*
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        moveToFragment(HomeFragment()) */


        val fragmentTrans = supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.fragment_container, HomeFragment())
        fragmentTrans.commit()




    }

   /* private fun moveToFragment(fragment: Fragment)
    {
        val fragmentTrans = supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.fragment_container, fragment)
        fragmentTrans.addToBackStack(null)
        fragmentTrans.commit()
    }
    */
}