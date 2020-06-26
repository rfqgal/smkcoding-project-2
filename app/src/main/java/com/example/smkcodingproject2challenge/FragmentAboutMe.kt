package com.example.smkcodingproject2challenge

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_about_me.*

class FragmentAboutMe: Fragment(), View.OnClickListener {

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

        btn_logout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_logout -> signOutConfirm()
            R.id.btn_edit -> {
                //edit profile
            }
        }
    }

    private fun signOutConfirm() {
        AlertDialog.Builder(activity)
            .setTitle("Logout")
            .setMessage("Apakah Anda yakin akan keluar?")
            .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which -> signOut() })
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
}