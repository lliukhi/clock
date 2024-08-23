package com.example.clock.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clock.R
import com.example.clock.data.model.Clock

class ClockAdapter(private val clockList: List<Clock>) : RecyclerView.Adapter<ClockAdapter.ViewHolder>() {
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val time: TextView = view.findViewById(R.id.time)
        val timeDiff: TextView = view.findViewById(R.id.timeDiff)
        val timeZone: TextView = view.findViewById(R.id.timeZone)
        val delete :TextView = view.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClockAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.time_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClockAdapter.ViewHolder, position: Int) {
        holder.time.text = clockList[position].time
        holder.timeZone.text = clockList[position].timeZone
//        holder.timeDiff.text = clockList[position].timeDiff
    }

    override fun getItemCount() = clockList.size
}