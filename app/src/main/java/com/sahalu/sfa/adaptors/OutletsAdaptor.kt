package com.sahalu.sfa.adaptors

import android.annotation.SuppressLint
import android.location.Location
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahalu.sfa.R
import com.sahalu.sfa.data.outlet.Outlets

class OutletsAdaptor (private val List: List<Outlets> = ArrayList())
    : RecyclerView.Adapter<OutletsViewHolder>() {

    var list: List<Outlets> = List
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutletsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OutletsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: OutletsViewHolder, position: Int) {
        val movie: Outlets = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

    fun setData(List: List<Outlets>){
        list = ArrayList()
        list = List
        notifyDataSetChanged()
    }

}

class OutletsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.outlet_item_view, parent, false)) {
    private var nameOutlet: TextView? = null
    private var outletDesc: TextView? = null
    private var distance: TextView? = null


    init {
        nameOutlet = itemView.findViewById(R.id.outletOwner)
        outletDesc = itemView.findViewById(R.id.outletDesc)
        distance = itemView.findViewById(R.id.distance)
    }

    @SuppressLint("SetTextI18n")
    fun bind(outlets: Outlets) {
        nameOutlet?.text = outlets.outlet_name
        outletDesc?.text = outlets.phone_number
        distance?.text = outlets.distance
    }


}