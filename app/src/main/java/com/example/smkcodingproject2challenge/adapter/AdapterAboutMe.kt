package com.example.smkcodingproject2challenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smkcodingproject2challenge.ProfileModel
import com.example.smkcodingproject2challenge.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_identity.view.*

class AdapterAboutMe(
    private val context: Context,
    private val list: ArrayList<ProfileModel>
): RecyclerView.Adapter<AdapterAboutMe.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(context).inflate(R.layout.item_identity , parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: ProfileModel) {
            containerView.tv_category.text = item.category
            containerView.tv_field.text = item.field
        }
    }
}