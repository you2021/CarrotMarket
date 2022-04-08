package com.example.carrotmarket.bottom01.registration

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carrotmarket.databinding.ItemImageBinding
import java.io.File
import java.util.ArrayList

class RegistrationAdapter(var items: ArrayList<Uri>) :  RecyclerView.Adapter<RegistrationAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: ItemImageBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Uri){

            Glide.with(binding.root.context).load(item).into(binding.picture)
        }

    }

}