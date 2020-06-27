package com.example.smkcodingproject2challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_about_me.*

class MainActivity : AppCompatActivity() {

    private val menuIcon = arrayOf(R.drawable.ic_covid_19, R.drawable.ic_global, R.drawable.ic_indonesia,
        R.drawable.ic_provinsi, R.drawable.ic_user)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "COVID-19 Global & Indonesia"

        val adapter = ViewPagerAdapter(this)
        view_pager.adapter = adapter
        TabLayoutMediator(tab_layout, view_pager, TabLayoutMediator.TabConfigurationStrategy {
                tab, position -> tab.icon = ResourcesCompat.getDrawable(resources, menuIcon[position], null)
        }).attach()
        /*
        if (intent.extras != null) {
            val fragment = intent.extras!!.getInt("fragment")

            when (fragment) {
                0 -> view_pager.currentItem = 0
                1 -> view_pager.currentItem = 1
                2 -> view_pager.currentItem = 2
                3 -> view_pager.currentItem = 3
                4 -> view_pager.currentItem = 4
                else -> {
                    view_pager.currentItem = 0
                }
            }
        }
        */
    }
}
