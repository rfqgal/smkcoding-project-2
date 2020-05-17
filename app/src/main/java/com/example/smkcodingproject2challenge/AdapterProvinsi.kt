package com.example.smkcodingproject2challenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smkcodingproject2challenge.api.Covid19ProvinsiItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_provinsi.*
import kotlinx.android.synthetic.main.item_provinsi.view.*

class AdapterProvinsi(
    private val context: Context, private val items: List<Covid19ProvinsiItem>,
    private val listener: (Covid19ProvinsiItem) -> Unit
) : RecyclerView.Adapter<AdapterProvinsi.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.item_provinsi, parent, false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(private val context: Context, override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: Covid19ProvinsiItem, listener: (Covid19ProvinsiItem) -> Unit) {
            val aktif = item.attributes.kasusPosi - item.attributes.kasusMeni - item.attributes.kasusSemb

            containerView.tv_provinsi_nama.text = item.attributes.provinsi
            containerView.tv_provinsi_positif.text = item.attributes.kasusPosi.toString()
            containerView.tv_provinsi_sembuh.text = item.attributes.kasusSemb.toString()
            containerView.tv_provinsi_meninggal.text = item.attributes.kasusMeni.toString()
            containerView.tv_provinsi_aktif.text = aktif.toString()

            containerView.setOnClickListener { listener(item) }
        }
    }
}