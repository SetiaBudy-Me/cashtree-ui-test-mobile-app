package com.vitiglobal.cashtreeagen.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitiglobal.cashtreeagen.R

class HomeBannerAdapter : RecyclerView.Adapter<HomeBannerAdapter.ViewHolder>() {

    data class Card(val id: Int)

    private val items = mutableListOf<Card>().apply {
        repeat(3) { add(Card(it)) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = items.size

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val mContext = view.context

        fun bind(position: Int) {

        }
    }
}