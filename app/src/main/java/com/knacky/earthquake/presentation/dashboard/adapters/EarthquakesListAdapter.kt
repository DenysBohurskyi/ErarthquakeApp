package com.knacky.earthquake.presentation.dashboard.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knacky.earthquake.R
import com.knacky.earthquake.data.entity.Earthquake
import kotlinx.android.synthetic.main.earthquake_info_item.view.*

/**
 * Created by knacky on 29.10.2018.
 */

class EarthquakesListAdapter(val context: Context, val earthquakesList: List<Earthquake>)
    : RecyclerView.Adapter<EarthquakesListAdapter.QuakesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuakesListViewHolder {
        return QuakesListViewHolder(LayoutInflater.from(context).inflate(R.layout.earthquake_info_item, parent, false))
    }

    override fun getItemCount() = earthquakesList.size


    override fun onBindViewHolder(holder: QuakesListViewHolder, position: Int) {
        holder.onBindView(position, earthquakesList[position])
    }

    class QuakesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindView(position: Int, earthquake: Earthquake) {
//            itemView.tag = position //for click
            itemView.quake_time_tv.text = earthquake.time.toString() //TODO: Map time correctly
            itemView.quake_address_tv.text = earthquake.address
            itemView.quake_magnitude_tv.text = earthquake.magnitude
        }

    }
}