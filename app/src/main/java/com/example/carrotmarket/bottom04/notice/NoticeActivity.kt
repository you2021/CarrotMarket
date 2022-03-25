package com.example.carrotmarket.bottom04.notice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom04.ManagerViewModel
import com.example.carrotmarket.databinding.ActivityNoticeBinding

class NoticeActivity : AppCompatActivity() {

    lateinit var binding : ActivityNoticeBinding
    lateinit var noticeViewModel : NoticeViewModel
    lateinit var managerViewModel: ManagerViewModel

    var adapter:NoticeAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticeBinding.inflate(layoutInflater)
        noticeViewModel = ViewModelProvider(this).get(NoticeViewModel::class.java)
        managerViewModel = ViewModelProvider(this).get(ManagerViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        setManagerObserver()
        managerViewModel.noticeFromServer()

        writeNoticeBtn()
    }

    override fun onResume() {
        super.onResume()

        noticeViewModel.noticeFromServer()
    }

    fun setObserver(){
        noticeViewModel.result.observe(this, Observer {
            adapter = NoticeAdapter(it)

            binding.noticeList.adapter = adapter
        })
    }

    fun setManagerObserver(){
        managerViewModel.result.observe(this, {
            if(it.manager == "1"){
                binding.writeNotice.visibility = View.VISIBLE
            }
        })
    }

    fun writeNoticeBtn(){
        binding.writeNotice.setOnClickListener {
            val intent = Intent(this, WriteNoticeActivity::class.java)
            startActivity(intent)
        }
    }
}