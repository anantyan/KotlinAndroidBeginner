package com.tutorial.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tutorial.databinding.ListMainBinding
import com.tutorial.models.MainModel

class MainAdapter(
    private var result: MutableList<MainModel>,
    private var listener: MainAdapterView
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(binding: ListMainBinding, listener: MainAdapterView?) : RecyclerView.ViewHolder(binding.root) {

        val txtJudul: TextView = binding.txtJudul
        val txtDeskripsi: TextView = binding.txtDeskripsi

        init {
            itemView.setOnClickListener {
                listener?.onClick(adapterPosition)
            }
            itemView.setOnLongClickListener {
                listener?.onLongClick(adapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListMainBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return result.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = result.get(position)
        holder.txtJudul.text = result.title
        holder.txtDeskripsi.text = result.overview
    }
}