package com.example.carrotmarket.bottom04.myPost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.R
import com.example.carrotmarket.bottom01.list.PostListAdapter
import com.example.carrotmarket.databinding.ActivityMyPostBinding

class myPostActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyPostBinding
    lateinit var myPostViewModel: MyPostViewModel

    var adapter : Adapter ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPostBinding.inflate(layoutInflater)
        myPostViewModel = ViewModelProvider(this).get(MyPostViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        myPostViewModel.getMyPostFromServer()
        back()
    }

    fun setObserver(){
        myPostViewModel.result.observe(this, Observer {
            adapter = Adapter(it)

            binding.postList.adapter = adapter
        })
    }

    fun back(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }
}