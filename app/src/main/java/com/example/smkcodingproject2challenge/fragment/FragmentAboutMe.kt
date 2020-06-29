package com.example.smkcodingproject2challenge.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.smkcodingproject2challenge.LoginActivity
import com.example.smkcodingproject2challenge.R
import com.example.smkcodingproject2challenge.UpdateUserActivity
import com.example.smkcodingproject2challenge.util.showToast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_about_me.*

class FragmentAboutMe: Fragment(), View.OnClickListener {

    private val user = FirebaseAuth.getInstance().currentUser
    private val name = user!!.displayName
    private val email = user!!.email
    private val photo = user!!.photoUrl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvMyName.text = name
        tvMyEmail.text = email

        if (tvMyName.text == "") {
            val anonymous = "Anonymous"
            tvMyName.text = anonymous
        }

        if (photo != null) {
            Glide.with(context!!)
                .load(photo)
                .into(imgMyPhoto)
        } else {
            Glide.with(context!!)
                .load(R.drawable.ic_user)
                .into(imgMyPhoto)
        }

        btn_logout.setOnClickListener(this)
        btn_edit.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_logout -> signOutConfirm()
            R.id.btn_edit -> updateProfile()
            R.id.btn_delete -> deleteConfirm()
        }
    }

    private fun signOutConfirm() {
        AlertDialog.Builder(activity)
            .setTitle("Logout")
            .setMessage("Apakah Anda yakin akan keluar?")
            .setPositiveButton("Ya") { dialog, which -> signOut() }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(activity, LoginActivity::class.java)
        Toast.makeText(activity, "Anda telah keluar", Toast.LENGTH_SHORT).show()
        startActivity(intent)
        activity!!.finish()
    }

    private fun updateProfile() {
        val intent = Intent(activity, UpdateUserActivity::class.java)
        if (name != null || email != null) {
            intent.putExtra("name", name)
            intent.putExtra("email", email)
        }
        startActivity(intent)
        activity!!.finish()
    }

    private fun deleteConfirm() {
        AlertDialog.Builder(activity)
            .setTitle("Hapus Akun")
            .setMessage("Apakah Anda yakin untuk menghapus akun Anda?")
            .setPositiveButton("Ya") { dialog, which -> deleteProfile() }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun deleteProfile() {
        FirebaseAuth.getInstance().currentUser!!.delete()
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    showToast(activity!!, "Akun Anda telah berhasil dihapus")
                    val intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                    activity!!.finish()
                }
            }
    }
}