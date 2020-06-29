package com.example.smkcodingproject2challenge.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.smkcodingproject2challenge.*
import com.example.smkcodingproject2challenge.R
import com.example.smkcodingproject2challenge.adapter.AdapterProfile
import com.example.smkcodingproject2challenge.util.showToast
import com.example.smkcodingproject2challenge.viewmodel.ProfileFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_about_me.*

class FragmentProfile: Fragment(), View.OnClickListener, AdapterProfile.dataListener {

    lateinit var ref: DatabaseReference
    lateinit var auth: FirebaseAuth

    var dataIdentitas: MutableList<ProfileModel> = ArrayList()
    private val viewModel by viewModels<ProfileFragmentViewModel>()
    private var adapter: AdapterProfile? = null

    private val user = FirebaseAuth.getInstance().currentUser
    private val name = user!!.displayName
    private val email = user!!.email
    private val photo = user!!.photoUrl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvMyName.text = name
        tvMyEmail.text = email

        if (tvMyName.text == "") {
            val anonymous = "Anonymous"
            tvMyName.text = anonymous
        }

        if (photo != null) {
            Glide.with(requireContext())
                .load(photo)
                .into(imgMyPhoto)
        } else {
            Glide.with(requireContext())
                .load(R.drawable.ic_user)
                .into(imgMyPhoto)
        }

        btn_logout.setOnClickListener(this)
        btn_edit.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
        btn_add.setOnClickListener(this)
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
        init()
        getData()
        viewModel.init(requireContext())
        viewModel.allIdentities.observe(viewLifecycleOwner, Observer { identities ->
            identities?.let { adapter?.setData(it) }
        })
    }

    private fun init() {
        rv_identity.layoutManager = LinearLayoutManager(context)
        adapter = AdapterProfile(requireContext(), dataIdentitas)
        rv_identity.adapter = adapter
        adapter?.listener = this

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_logout -> signOutConfirm()
            R.id.btn_edit -> updateProfile()
            R.id.btn_delete -> deleteConfirm()
            R.id.btn_add -> addProfile()
        }
    }

    private fun addProfile() {
        val intent = Intent(activity, AddIdentityActivity::class.java)
        startActivity(intent)
    }

    private fun getData() {
        showToast(requireContext(), "Mohon tunggu...")
        auth = FirebaseAuth.getInstance()

        val getUserID = auth.currentUser?.uid.toString()
        ref = FirebaseDatabase.getInstance().reference

        ref.child(getUserID).child("Identity").addValueEventListener(object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                showToast(context!!, "Database error...")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataIdentitas = ArrayList()
                for (snapshot in dataSnapshot.children) {
                    val identity = snapshot.getValue(ProfileModel::class.java)
                    identity?.key = snapshot.key.toString()
                    dataIdentitas.add(identity!!)
                }
                viewModel.insertAll(dataIdentitas)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

    override fun onDeleteData(profileModel: ProfileModel, position: Int) {
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.currentUser?.uid.toString()
        if (ref != null) {
            ref.child(getUserID)
                .child("Identity")
                .child(profileModel?.key!!.toString())
                .removeValue()
                .addOnSuccessListener {
                    showToast(requireContext(), "Data berhasil dihapus")
                    viewModel.delete(profileModel)
                }
        } else {
            showToast(requireContext(), profileModel!!.key!!.toString())
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
        activity?.finish()
    }

    private fun updateProfile() {
        val intent = Intent(activity, UpdateUserActivity::class.java)
        if (name != null || email != null) {
            intent.putExtra("name", name)
            intent.putExtra("email", email)
        }
        startActivity(intent)
        activity?.finish()
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
                    showToast(requireActivity(), "Akun Anda telah berhasil dihapus")
                    val intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
    }
}