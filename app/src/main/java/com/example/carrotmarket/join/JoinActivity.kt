package com.example.carrotmarket.join

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivityJoinBinding
import com.example.carrotmarket.login.LoginViewModel

class JoinActivity : AppCompatActivity() {

    lateinit var binding : ActivityJoinBinding
    lateinit var joinViewModel: JoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        joinViewModel = ViewModelProvider(this).get(JoinViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        joinBtn()

    }

    private fun setObserver() {
        joinViewModel.result.observe(this, Observer {
            if (it.status == "success") {
                finish()
            }else{
                Toast.makeText(this, "중복된 아이디 입니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun joinBtn(){
        if(binding.userId.text != null){
            binding.joinBtn.setOnClickListener {
                joinViewModel.joinInfoToServer(binding.userId.text.toString(),binding.userPw.text.toString(),binding.userName.text.toString(),)
            }
        }

    }
}