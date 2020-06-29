package com.example.smkcodingproject2challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.smkcodingproject2challenge.util.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update_identity.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
class UpdateIdentityActivity : AppCompatActivity() {

    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var newCategory: String? = null
    private var newField: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_identity)

        title = "Update Identity"

        progress.visibility = View.GONE
        supportActionBar?.title = "Update Data"

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        getData()

        btn_cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_update.setOnClickListener {
            newCategory = new_category.text.toString()
            newField = new_field.text.toString()

            if (validateForm(newCategory!!, newField!!)) {
                progress.visibility = View.VISIBLE

                val getKey: String = intent.getStringExtra("key").toString()
                val newData = ProfileModel(newCategory!!, newField!!, getKey)
                val getUserID: String = auth?.currentUser?.uid.toString()

                database!!.child(getUserID).child("Identity").child(getKey).setValue(newData)
                    .addOnCompleteListener {
                        showToast(this, "Data berhasil disimpan")
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
            }
        }
    }

    private fun getData() {
        val getCategory: String = intent.getStringExtra("category").toString()
        val getField: String = intent.getStringExtra("field").toString()

        new_category?.setText(getCategory)
        new_field?.setText(getField)
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
