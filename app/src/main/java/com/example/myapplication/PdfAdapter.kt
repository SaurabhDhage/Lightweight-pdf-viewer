package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PdfAdapter(private val list: ArrayList<Pdf>, private val listener: PdfClickListener) :
        RecyclerView.Adapter<PdfAdapter.PdfViewHolder>() {
    inner class PdfViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.artView)
        val pdfName: TextView = itemView.findViewById(R.id.music_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, null)
        val viewHolder = PdfViewHolder(view)

        view.setOnClickListener {
            val item = list[viewHolder.adapterPosition]
            listener.onPdfClick(item)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PdfViewHolder, position: Int) {
        val item = list[position]

        holder.pdfName.text = item.name

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface PdfClickListener {
    fun onPdfClick(pdf: Pdf)
}