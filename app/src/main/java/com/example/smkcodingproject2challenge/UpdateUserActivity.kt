package com.example.smkcodingproject2challenge

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.SpannableStringBuilder
import android.view.View
import com.example.smkcodingproject2challenge.util.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_update_user.*
import java.io.FileNotFoundException
import java.io.IOException
import android.graphics.Bitmap as Bitmap

class UpdateUserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var newPhoto: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)

        progress.visibility = View.GONE

        val name = intent.getStringExtra("name")

        val editableName = SpannableStringBuilder(name)

        edt_name.text = editableName

        btn_cancel.setOnClickListener(this)
        btn_update.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_cancel -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btn_update -> updateUserProfile()
        }
    }

    private fun updateUserProfile() {
        val user = FirebaseAuth.getInstance().currentUser
        val newName = edt_name.text.toString()

        if (newName != null || newPhoto != null) {
            progress.visibility = View.VISIBLE

            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(newName)
                .build()

            user?.updateProfile(profileUpdates)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast(this, "Profil telah berhasil diupdate")
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
        }
    }
}
