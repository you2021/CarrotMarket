package com.example.carrotmarket.bottom02.types

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom02.townList.TownListAdapter
import com.example.carrotmarket.databinding.ActivityTypeBinding

class TypeActivity : AppCompatActivity() {
    lateinit var binding:ActivityTypeBinding
    private lateinit var typeListViewModel: TypeViewModel

    var adapter: TypeAdapter? = null
    var type = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeBinding.inflate(layoutInflater)
        typeListViewModel = ViewModelProvider(this).get(TypeViewModel::class.java)
        setContentView(binding.root)

        type = intent.getStringExtra("text")!!
        binding.text.text = type

        setListObserver()
        list()
        back()
    }

    fun setListObserver(){
        typeListViewModel.Result.observe(this, Observer {
            adapter = TypeAdapter(it)

            binding.chattingContent.adapter = adapter
        })
    }

    fun list(){
        typeListViewModel.listFromServer(type)
    }

    fun back(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }
}