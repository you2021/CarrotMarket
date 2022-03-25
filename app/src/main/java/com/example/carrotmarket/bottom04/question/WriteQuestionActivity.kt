package com.example.carrotmarket.bottom04.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivityQuestionBinding

class WriteQuestionActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuestionBinding
    lateinit var writeQuestionViewModel: WriteQuestionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        writeQuestionViewModel = ViewModelProvider(this).get(WriteQuestionViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        okBtn()

    }
    fun setObserver(){
        writeQuestionViewModel.result.observe(this, {
            if (it.status == "success"){
                finish()
            }
        })
    }
    
    fun okBtn(){
        binding.okBtn.setOnClickListener {
            writeQuestionViewModel.questionToServer(binding.tittle.text.toString(),binding.etComment.text.toString(),)
        }
    }
}