package com.example.carrotmarket.bottom04.question

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.carrotmarket.databinding.QuestionItemBinding
import java.util.ArrayList

class QuestionListAdapter(var items: ArrayList<QuestionItem>) :  RecyclerView.Adapter<QuestionListAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = QuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: QuestionItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item : QuestionItem){
            binding.tittle.text = item.tittle
            binding.userId.text = item.user_id
            binding.dateTime.text = item.dateTime
    
            itemView.setOnClickListener {
                val pos:Int = getLayoutPosition()
                val intent = Intent(binding.root.context, QuestionDetailActivity::class.java)
                intent.putExtra("num", pos)
                intent.putExtra("no", item.no)
                binding.root.context.startActivity(intent)
            }
        }

    }

}