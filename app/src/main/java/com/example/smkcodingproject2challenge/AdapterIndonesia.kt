package com.example.smkcodingproject2challenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smkcodingproject2challenge.api.Covid19IndonesiaItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_indonesia.*
import kotlinx.android.synthetic.main.item_indonesia.view.*

class AdapterIndonesia(
    private val context: Context, private val items: List<Covid19IndonesiaItem>,
    private val listener: (Covid19IndonesiaItem) -> Unit
) : RecyclerView.Adapter<AdapterIndonesia.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.item_indonesia, parent, false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AdapterIndonesia.ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(val context: Context, override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: Covid19IndonesiaItem, listener: (Covid19IndonesiaItem) -> Unit) {
            containerView.tv_indo_dirawat.text = item.dirawat
            containerView.tv_indo_meninggal.text = item.meninggal
            containerView.tv_indo_positif.text = item.positif
            containerView.tv_indo_sembuh.text = item.sembuh
        }
    }
}