package com.example.carrotmarket.bottom04.notice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivityWriteNoticeBinding

class WriteNoticeActivity : AppCompatActivity() {

    lateinit var binding:ActivityWriteNoticeBinding
    lateinit var writeNoticeViewModel: WriteNoticeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteNoticeBinding.inflate(layoutInflater)
        writeNoticeViewModel = ViewModelProvider(this).get(WriteNoticeViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        okBtn()

    }

    fun setObserver(){
        writeNoticeViewModel.result.observe(this, {
            if(it.status == "success"){
                finish()
            }
        })
    }

    fun okBtn(){
        binding.okBtn.setOnClickListener {
            writeNoticeViewModel.writeNoticeToServer(binding.tittle.text.toString(), binding.etComment.text.toString())
        }
    }
}