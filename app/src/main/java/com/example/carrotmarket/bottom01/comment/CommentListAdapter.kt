package com.example.carrotmarket.bottom01.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrotmarket.databinding.CommentItemBinding
import java.util.ArrayList

class CommentListAdapter(var items: ArrayList<CommentListItem>) :  RecyclerView.Adapter<CommentListAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: CommentItemBinding):RecyclerView.ViewHolder(binding.root){
//        context => binding.root.context
        fun bind(item : CommentListItem){
            binding.userId.text = item.user_id
            binding.commentTxt.text = item.comment
        }

    }

}