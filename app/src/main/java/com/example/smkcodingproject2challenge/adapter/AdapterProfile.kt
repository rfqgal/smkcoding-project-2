package com.example.smkcodingproject2challenge.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smkcodingproject2challenge.ProfileModel
import com.example.smkcodingproject2challenge.R
import com.example.smkcodingproject2challenge.ProfileUpdateActivity
import com.example.smkcodingproject2challenge.util.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_identity.view.*

class AdapterProfile(
    private val context: Context,
    private val list: ArrayList<ProfileModel>
): RecyclerView.Adapter<AdapterProfile.ViewHolder>() {

    public var listener: dataListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(context).inflate(R.layout.item_identity , parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])

        lateinit var ref: DatabaseReference
        lateinit var auth: FirebaseAuth

        holder.containerView.btn_more.setOnClickListener { v: View? ->
            val action = arrayOf("Update", "Delete")
            val alert = AlertDialog.Builder(v!!.context)
            alert.setItems(action) { dialog, i ->
                when (i) {
                    0 -> {
                        val bundle = Bundle()
                        bundle.putString("category", list[position].category)
                        bundle.putString("field", list[position].field)
                        bundle.putString("key", list[position].key)
                        val intent = Intent(context, ProfileUpdateActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    1 -> {
                        auth = FirebaseAuth.getInstance()
                        ref = FirebaseDatabase.getInstance().reference
                        val getUserID: String = auth.currentUser?.uid.toString()
                        if (ref != null) {
                            ref.child(getUserID)
                                .child("Identity")
                                .child(list[position].key)
                                .removeValue()
                                .addOnCompleteListener {
                                    showToast(context, "Data berhasil dihapus")
                                }
                        }
                    }
                }
            }
            alert.create()
            alert.show()
            true
        }
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: ProfileModel) {
            containerView.tv_category.text = item.category
            containerView.tv_field.text = item.field
        }
    }

    internal fun setData(newData: List<ProfileModel>) {
        this.setData(newData)
        notifyDataSetChanged()
    }
}

