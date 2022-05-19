package com.example.carrotmarket.bottom03.chattingroom

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.carrotmarket.bottom03.chatting.ChattingActivity
import com.example.carrotmarket.databinding.ChattingRoomItemBinding


class ChattingRoomAdapter(var items: ArrayList<ChattingRoomItem>) :
    RecyclerView.Adapter<ChattingRoomAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding =
            ChattingRoomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: ChattingRoomItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChattingRoomItem) {
            val id = binding.root.context.getSharedPreferences("id", AppCompatActivity.MODE_PRIVATE).getString("myId","")

            if (id == item.your_id) binding.yourId.text = item.my_id
            else binding.yourId.text = item.your_id

            val date = item.dateTime.replace("T"," ")
            val time = date.slice(IntRange(0,18))
            binding.time.text = time

            itemView.setOnClickListener {
                val intent = Intent(binding.root.context, ChattingActivity::class.java)
                intent.putExtra("roomKey", item.roomKey.toInt())
                intent.putExtra("userId", binding.yourId.text)
                binding.root.context.startActivity(intent)
            }
        }

    }

}