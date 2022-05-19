package com.example.carrotmarket.bottom02.types

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.carrotmarket.bottom02.townList.TownItem
import com.example.carrotmarket.databinding.TownListItemBinding
import java.util.ArrayList

class TypeAdapter(var items: ArrayList<TownItem>) :  RecyclerView.Adapter<TypeAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = TownListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: TownListItemBinding):RecyclerView.ViewHolder(binding.root){
//        context => binding.root.context
        fun bind(item : TownItem){
            binding.typeTxt.text = item.type
            binding.contents.text = item.contents

            val date = item.time.replace("T"," ")
            val time = date.slice(IntRange(0,18))
            binding.time.text = time

        }

    }

}