package com.example.smkcodingproject2challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

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
                tab, position ->
            tab.icon = ResourcesCompat.getDrawable(resources, menuIcon[position], null)
        }).attach()
    }
}
