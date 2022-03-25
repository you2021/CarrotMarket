package com.example.carrotmarket.bottom04.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivityAnswerBinding

class WriteAnswerActivity : AppCompatActivity() {

    lateinit var binding : ActivityAnswerBinding
    lateinit var writeAnswerViewModel: WriteAnswerViewModel

    var no = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        writeAnswerViewModel = ViewModelProvider(this).get(WriteAnswerViewModel::class.java)
        setContentView(binding.root)

        no = intent.getStringExtra("no")!!

        setObserver()
        okBtn()
    }

    fun setObserver(){
        writeAnswerViewModel.result.observe(this, {
            if(it.status == "success") finish()
        })
    }

    fun okBtn(){
        binding.okBtn.setOnClickListener {
            writeAnswerViewModel.answerToServer(no.toInt(), binding.etComment.text.toString())
        }
    }
}