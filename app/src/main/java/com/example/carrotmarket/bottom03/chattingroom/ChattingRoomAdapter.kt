package com.example.carrotmarket.bottom03.chattingroom

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carrotmarket.R
import com.example.carrotmarket.bottom01.detail.DetailActivity
import com.example.carrotmarket.bottom03.ChattingActivity
import com.example.carrotmarket.databinding.ChattingRoomItemBinding
import com.example.carrotmarket.databinding.ListItemBinding
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL


class ChattingRoomAdapter(var items: ArrayList<ChattingRoomItem>) :  RecyclerView.Adapter<ChattingRoomAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ChattingRoomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: ChattingRoomItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item : ChattingRoomItem){
            binding.yourId.text = item.your_id
            binding.time .text = item.dateTime

            itemView.setOnClickListener {

                    val intent = Intent(binding.root.context, ChattingActivity::class.java)
                    intent.putExtra("room", item.room)
                    intent.putExtra("userId", "")
                    intent.putExtra("init", "")
                    binding.root.context.startActivity(intent)

            }
        }

    }

}