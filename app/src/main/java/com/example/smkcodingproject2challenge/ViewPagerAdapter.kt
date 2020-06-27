package com.example.smkcodingproject2challenge

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private val TOTAL_MENU = 5

    override fun getItemCount(): Int {
        return TOTAL_MENU
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> { return FragmentNews() }
            1 -> { return FragmentGlobal() }
            2 -> { return FragmentIndonesia() }
            3 -> { return FragmentProvinsi() }
            4 -> { return FragmentAboutMe() }
            else -> {
                return FragmentNews()
            }
        }
    }

    private fun getPageTitle(position: Int): CharSequence {
        val titleList = arrayOf(
            "Tentang Covid-19",
            "Data Global",
            "Data Indonesia",
            "Data Provinsi",
            "Profil User"
        )
        return titleList[position]
    }
}