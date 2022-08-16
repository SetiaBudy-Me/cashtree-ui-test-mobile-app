package com.vitiglobal.cashtreeagen.view.onboarding

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vitiglobal.cashtreeagen.R
import com.vitiglobal.cashtreeagen.view.home.HomeActivity

class OnboardingAdapter : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

    data class Card(val id: Int)

    private val items = mutableListOf<Card>().apply {
        repeat(3) { add(Card(it)) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_onboarding, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = items.size

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val mContext = view.context
        private val benefits = arrayListOf(
            "Benefit 1. Lorem ipsum dolor sit amet",
            "Benefit 2. Lorem ipsum dolor sit amet",
            "Benefit 3. Lorem ipsum dolor sit amet"
        )

        fun bind(position: Int) {
            val tvBenefit = view.findViewById<TextView>(R.id.tv_benefit)
            val tvButtonHome = view.findViewById<TextView>(R.id.tv_button_home)

            tvBenefit.text = benefits[position]

            if (position != 2) {
                tvButtonHome.visibility = View.INVISIBLE
            } else {
                tvButtonHome.visibility = View.VISIBLE
                tvButtonHome.setOnClickListener {
                    val intent = Intent(mContext, HomeActivity::class.java)
                    mContext.startActivity(intent)
                }
            }
        }
    }
}