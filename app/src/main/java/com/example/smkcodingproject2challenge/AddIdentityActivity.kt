package com.example.smkcodingproject2challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.activity.viewModels
import com.example.smkcodingproject2challenge.util.showToast
import com.example.smkcodingproject2challenge.viewmodel.ProfileUpdateViewModel
import com.example.smkcodingproject2challenge.viewmodel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_identity.*

class AddIdentityActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    private var auth: FirebaseAuth? = null

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_identity)

        title = "Add Identity"

        progress.visibility = View.GONE

        viewModel.init(this)
        ref = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        btn_save.setOnClickListener{ saveData() }
        btn_cancel.setOnClickListener { finish() }
    }

    private fun saveData() {
        val getCategory: String = edt_category?.text.toString()
        val getField: String = edt_field?.text.toString()
        val getUserID: String = auth?.currentUser?.uid.toString()
        var key = 0
        key++

        if (validateForm(getCategory, getField)) {
            progress.visibility = View.VISIBLE

            val identity = ProfileModel(getCategory, getField, key.toString())
            ref.child(getUserID).child("Identity").push().setValue(identity).addOnCompleteListener {
                showToast(this, "Data Berhasil Disimpan")
                viewModel.addData(identity)
                finish()
            }
        }
    }

    private fun validateForm(category: String, field: String): Boolean {
        if (TextUtils.isEmpty(category)) {
            showToast(applicationContext, "Enter category!")
            return false
        }
        if (TextUtils.isEmpty(field)) {
            showToast(applicationContext, "Enter field!")
            return false
        }
        return true
    }
}
