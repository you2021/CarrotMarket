package com.example.carrotmarket.bottom04.notice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivityNoticeDetailBinding

class NoticeDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityNoticeDetailBinding
    lateinit var noticeDetailModel: NoticeDetailViewModel

    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticeDetailBinding.inflate(layoutInflater)
        noticeDetailModel = ViewModelProvider(this).get(NoticeDetailViewModel::class.java)
        setContentView(binding.root)

        num = intent.getIntExtra("num", 0)

        setDetailObserber()
        noticeDetailModel.noticeDetailFromServer(num)
    }

    fun setDetailObserber(){
        noticeDetailModel.result.observe(this, {
            binding.tittle.text = it.tittle
            binding.content.text = it.content

            val date = it.dateTime.replace("T"," ")
            val time = date.slice(IntRange(0,18))
            binding.dateTime.text = time

        })
    }
}