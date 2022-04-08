package com.example.carrotmarket.bottom01.detail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.carrotmarket.R
import java.util.ArrayList

class ImageAdapter(var context: Context, var img : ArrayList<String>) : PagerAdapter() {
    override fun getCount(): Int {
        return img.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_detail_image,null)

        var iv = view.findViewById<ImageView>(R.id.image)
        if (img.size == 1 && img.get(0).equals("")) Glide.with(context).load(R.drawable.no_image).into(iv)
        else {
            val image_url = context.getString(R.string.common_image_url)
            Glide.with(context).load(image_url + img.get(position)).into(iv)
        }

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view == `object`
    }

}