package com.example.carrotmarket.bottom04.interestList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom04.myPost.Adapter
import com.example.carrotmarket.bottom04.myPost.MyPostViewModel
import com.example.carrotmarket.databinding.ActivityInterestListBinding

class InterestListActivity : AppCompatActivity() {
    lateinit var binding : ActivityInterestListBinding
    lateinit var listViewModel: ListViewModel

    var adapter : ListAdapter ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterestListBinding.inflate(layoutInflater)
        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        listViewModel.interestFavoriteFromServer()

        back()
    }

    fun setObserver(){
        listViewModel.result.observe(this, Observer {
            adapter = ListAdapter(it)

            binding.postList.adapter = adapter
        })
    }

    fun back(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }
}