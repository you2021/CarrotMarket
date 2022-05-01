package com.example.carrotmarket.bottom02.townList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.carrotmarket.databinding.TownListItemBinding
import java.util.ArrayList

class TownListAdapter(var items: ArrayList<TownItem>) :  RecyclerView.Adapter<TownListAdapter.VH>(){

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
            binding.time.text = item.time
    
//            itemView.setOnClickListener {
//                val pos:Int = getLayoutPosition()
//                val intent = Intent(binding.root.context, DetailActivity::class.java)
//                intent.putExtra("num", pos)
//                binding.root.context.startActivity(intent)
//            }
        }

    }

}