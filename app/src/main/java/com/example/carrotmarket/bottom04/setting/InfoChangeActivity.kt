package com.example.carrotmarket.bottom04.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivityInfoChangeBinding
import com.example.carrotmarket.login.LoginActivity

class InfoChangeActivity : AppCompatActivity() {

    lateinit var binding : ActivityInfoChangeBinding
    lateinit var changeViewModel: InfoChangeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoChangeBinding.inflate(layoutInflater)
        changeViewModel = ViewModelProvider(this).get(InfoChangeViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        changeBtn()
    }

    private fun setObserver() {
        changeViewModel.result.observe(this, Observer {
            if (it.status == "success") {
                Toast.makeText(this, "회원정보가 변경되었습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun changeBtn(){
        binding.joinBtn.setOnClickListener {
            changeViewModel.changeToServer(binding.userPw.text.toString(), binding.userName.text.toString())
        }
    }
}