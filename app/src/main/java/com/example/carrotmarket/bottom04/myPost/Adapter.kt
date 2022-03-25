package com.example.carrotmarket.bottom04.myPost

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrotmarket.bottom01.detail.DetailActivity
import com.example.carrotmarket.bottom01.list.PostListItem
import com.example.carrotmarket.databinding.ListItemBinding
import java.util.ArrayList

class Adapter(var items: ArrayList<PostListItem>) :  RecyclerView.Adapter<Adapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item : PostListItem){
            binding.tittleTxt.text = item.tittle
            binding.dateTimeTxt.text = item.created_dt
            binding.priceTxt.text = "${item.price}원"
    
            itemView.setOnClickListener {
                val pos:Int = getLayoutPosition()
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("num", pos)
                binding.root.context.startActivity(intent)
            }
        }

    }

}