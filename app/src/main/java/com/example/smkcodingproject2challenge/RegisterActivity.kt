package com.example.smkcodingproject2challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.smkcodingproject2challenge.util.showToast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val TAG = "FirebaseEmailPassword"
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        btn_register.setOnClickListener {
            createAccount(edt_email.text.toString(), edt_password.text.toString())
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.e(TAG, "createAccount:" + email)
        if (!validateForm(email, password)) {
            return
        }

        auth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.e(TAG, "createAccount: Success!")
                    finish()
                } else {
                    Log.e(TAG, "createAccount: Fail!", task.exception)
                    showToast(applicationContext, "Authentication failed!")
                }
            }
    }

    private fun validateForm(email: String, password: String): Boolean {

        if (TextUtils.isEmpty(email)) {
            showToast(applicationContext, "Enter email address!")
            return false
        }

        if (TextUtils.isEmpty(password)) {
            showToast(applicationContext, "Enter password!")
            return false
        }

        if (password.length < 6) {
            showToast(applicationContext, "Password too short, enter minimum 6 characters!")
            return false
        }

        return true
    }
}
