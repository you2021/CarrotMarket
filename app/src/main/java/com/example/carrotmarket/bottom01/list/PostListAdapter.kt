package com.example.carrotmarket.bottom01.list

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carrotmarket.R
import com.example.carrotmarket.bottom01.detail.DetailActivity
import com.example.carrotmarket.databinding.ListItemBinding
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL


class PostListAdapter(var items: ArrayList<PostListItem>) :  RecyclerView.Adapter<PostListAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
//        context => binding.root.context
        fun bind(item : PostListItem){
            binding.tittleTxt.text = item.tittle
            binding.dateTimeTxt.text = item.created_dt
            binding.priceTxt.text = "${item.price}원"
            try {
                val image_url = binding.tittleTxt.context.getString(R.string.common_image_url)
                Glide.with(binding.iv.context)
                    .load(image_url+"test_image1648027038933.jpg")
                    .into(binding.iv)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
    
            itemView.setOnClickListener {
                val pos:Int = getLayoutPosition()
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("num", pos)
                binding.root.context.startActivity(intent)
            }
        }

    }

}