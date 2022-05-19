package com.example.carrotmarket.bottom04.myPost

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carrotmarket.R
import com.example.carrotmarket.bottom01.detail.DetailActivity
import com.example.carrotmarket.bottom01.list.PostListItem
import com.example.carrotmarket.databinding.ListItemBinding
import java.net.MalformedURLException
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
            binding.priceTxt.text = "${item.price}원"

            val date = item.created_dt.replace("T"," ")
            val time = date.slice(IntRange(0,18))
            binding.dateTimeTxt.text = time

            try {
                if (item.image == null || item.image == "") Glide.with(binding.iv.context).load(R.drawable.no_image).into(binding.iv)
                else{
                    val imageArr = item.image.split(",")
                    Log.d("imageArr", imageArr[0])
                    val image_url = binding.tittleTxt.context.getString(R.string.common_image_url)
                    Glide.with(binding.iv.context)
                        .load(image_url+imageArr[0])
                        .into(binding.iv)
                }

            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
    
            itemView.setOnClickListener {
                val pos:Int = getLayoutPosition()
                val intent = Intent(binding.root.context, MyDetailActivity::class.java)
                intent.putExtra("num", item.id.toInt())
                binding.root.context.startActivity(intent)
            }
        }

    }

}