package com.hashcode.chocshop.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.hashcode.chocshop.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fragmentManager = supportFragmentManager
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_shop -> {
                //Display the Shop Fragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                //Display the User Profile Fragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_chat -> {
                //Display the Chat Fragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fragment = fragmentManager.findFragmentById(R.id.main_container)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
