package com.example.smkcodingproject2challenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smkcodingproject2challenge.api.Covid19NewsIndonesiaArticle
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.view.*

class AdapterNews(
    private val context: Context, private val items: List<Covid19NewsIndonesiaArticle>,
    private val listener: (Covid19NewsIndonesiaArticle) -> Unit
) : RecyclerView.Adapter<AdapterNews.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.item_news, parent, false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(val context: Context, override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: Covid19NewsIndonesiaArticle, listener: (Covid19NewsIndonesiaArticle) -> Unit) {
            Glide.with(context)
                .load(item.urlToImage)
                .into(containerView.img_news)
            containerView.tv_title.text = item.title
            containerView.tv_date.text = item.publishedAt
            containerView.tv_author.text = item.author

            containerView.setOnClickListener { listener(item) }
        }
    }
}