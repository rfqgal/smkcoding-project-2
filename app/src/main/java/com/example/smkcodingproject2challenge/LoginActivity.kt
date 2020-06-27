package com.example.smkcodingproject2challenge

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.smkcodingproject2challenge.util.showToast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var auth: FirebaseAuth? = null
    private var RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progress.visibility = View.GONE

        auth = FirebaseAuth.getInstance() //Mendapatkan instance Firebase Auth

        btn_login_email.setOnClickListener(this)
        btn_sign_up.setOnClickListener(this)
        btn_login_google.setOnClickListener(this)

        if (auth!!.currentUser == null) {
        } else {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                showToast(this, "Login Berhasil")
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                progress.visibility = View.GONE
                showToast(this, "Login Dibatalkan")
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_login_google -> signInGoogle()
            R.id.btn_login_email -> signIn()
            R.id.btn_sign_up -> {
                val intent = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun signInGoogle() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN
        )
        progress.visibility = View.VISIBLE
    }

    private fun signIn() {
        if (!validateForm(edt_email.text.toString(), edt_password.text.toString())) {
            return
        }
        auth!!.signInWithEmailAndPassword(edt_email.text.toString(), edt_password.text.toString())
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    showToast(this, "Login Gagal")
                } else {
                    showToast(this, "Login Berhasil")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            .addOnFailureListener {
                Log.d("Main", "Failed Login: ${it.message}")
                showToast(this, "Email/Password incorrect")
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


