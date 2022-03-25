package com.example.carrotmarket.bottom04.notice

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrotmarket.databinding.NoticeItemBinding
import java.util.ArrayList

class NoticeAdapter(var items: ArrayList<NoticeItem>) :  RecyclerView.Adapter<NoticeAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = NoticeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: NoticeItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item : NoticeItem){
            binding.tittle.text = item.tittle
            binding.dateTime.text = item.dateTime
    
            itemView.setOnClickListener {
                val pos:Int = getLayoutPosition()
                val intent = Intent(binding.root.context, NoticeDetailActivity::class.java)
                intent.putExtra("num", pos)
                binding.root.context.startActivity(intent)
            }
        }

    }

}