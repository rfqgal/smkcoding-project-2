package com.example.smkcodingproject2challenge

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.example.smkcodingproject2challenge.util.showToast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val menuIcon = arrayOf(R.drawable.ic_covid_19, R.drawable.ic_global, R.drawable.ic_indonesia,
        R.drawable.ic_provinsi, R.drawable.ic_profile)

    private val TAG = "FIREBASE MESSAGING"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "COVID-19 Global & Indonesia"

        val adapter = ViewPagerAdapter(this)
        view_pager.adapter = adapter
        TabLayoutMediator(tab_layout, view_pager, TabLayoutMediator.TabConfigurationStrategy {
                tab, position -> tab.icon = ResourcesCompat.getDrawable(resources, menuIcon[position], null)
        }).attach()

        firebaseMessaging()
    }

    @SuppressLint("StringFormatInvalid")
    private fun firebaseMessaging() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceID failed", task.exception)
                    return@addOnCompleteListener
                }
                val token = task.result?.token
                val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, msg)
                showToast(baseContext, msg)
            }
    }
}
