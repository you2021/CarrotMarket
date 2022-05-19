package com.example.carrotmarket.bottom03.chatting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.carrotmarket.R
import java.util.ArrayList

class ChattingAdapter(val context: Context, var messages: ArrayList<ChattingItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

   val VIEW_TYPE_MY_MESSAGE = 1
   val VIEW_TYPE_OTHER_MESSAGE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == VIEW_TYPE_MY_MESSAGE){
            val view : View = LayoutInflater.from(context).inflate(R.layout.my_messagebox_item,parent, false)
            return MyMessageViewHolder(view)
        }else{
            val view : View = LayoutInflater.from(context).inflate(R.layout.other_messagebox_item,parent, false)
            return OtherMessageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messages[position]
        if(holder.javaClass == MyMessageViewHolder::class.java){
            val viewHolder = holder as MyMessageViewHolder
            holder.msg.text = currentMessage.comment
            holder.id.text = currentMessage.id
            holder.time.text = currentMessage.dateTime
        }else{
            val viewHolder = holder as OtherMessageViewHolder
            holder.msg.text = currentMessage.comment
            holder.id.text = currentMessage.id
            holder.time.text = currentMessage.dateTime
        }
    }

    override fun getItemCount(): Int = messages.size


    override fun getItemViewType(position: Int): Int {
        val currentMessage = messages[position]
        val id = (context as ChattingActivity).saveId
        if (id == currentMessage.id){
            return VIEW_TYPE_MY_MESSAGE
        }else{
            return VIEW_TYPE_OTHER_MESSAGE
        }




    }

    class MyMessageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var id = view.findViewById<TextView>(R.id.tv_name)
        var msg = view.findViewById<TextView>(R.id.tv_msg)
        var time = view.findViewById<TextView>(R.id.tv_time)


    }

    class OtherMessageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var id = view.findViewById<TextView>(R.id.tv_name)
        var msg = view.findViewById<TextView>(R.id.tv_msg)
        var time = view.findViewById<TextView>(R.id.tv_time)


    }


}
