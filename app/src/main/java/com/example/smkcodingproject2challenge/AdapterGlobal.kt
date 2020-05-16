package com.example.smkcodingproject2challenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smkcodingproject2challenge.api.Covid19GlobalItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_global.view.*

class AdapterGlobal(
    private val context: Context, private val items: List<Covid19GlobalItem>,
    private val listener: (Covid19GlobalItem) -> Unit
) : RecyclerView.Adapter<AdapterGlobal.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.item_global, parent, false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AdapterGlobal.ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(val context: Context, override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: Covid19GlobalItem, listener: (Covid19GlobalItem) -> Unit) {
            containerView.tv_global_country.text = item.attributes.countryRegion
            containerView.tv_global_confirmed.text = item.attributes.confirmed.toString()
            containerView.tv_global_deaths.text = item.attributes.deaths.toString()
            containerView.tv_global_recovered.text = item.attributes.recovered.toString()
            containerView.tv_global_active.text = item.attributes.active.toString()

            containerView.setOnClickListener { listener(item) }
        }
    }
}