package com.example.stylesync.ui.closet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stylesync.R
import com.example.stylesync.data.remote.response.data

class ClosetAdapter (private val closetItems: MutableList<data>) :
    RecyclerView.Adapter<ClosetAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textColor: TextView = itemView.findViewById(R.id.textColor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_closet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = closetItems[position]

        holder.textTitle.text = item.namaItem
        holder.textColor.text = item.warnaId.toString()
    }

    override fun getItemCount(): Int {
        return closetItems.size
    }

    fun submitList(newList: List<data>) {
        closetItems.clear()
        closetItems.addAll(newList)
        notifyDataSetChanged()
    }
}