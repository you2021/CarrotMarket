package com.example.carrotmarket.bottom04.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivitySettingBinding
import com.example.carrotmarket.login.LoginActivity

class SettingActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingBinding
    lateinit var logout : LogoutViewModel
    lateinit var dialog:AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        logout = ViewModelProvider(this).get(LogoutViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        logoutBtn()
        infoChange()

        back()

    }

    fun infoChange(){
        binding.infoChange.setOnClickListener {
            val intent = Intent(this, InfoChangeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setObserver() {
        logout.result.observe(this, Observer {
            if (it.status == "success") {
                getSharedPreferences("login", MODE_PRIVATE).edit().clear().apply()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    fun logoutBtn(){
        binding.logoutBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("로그아웃 하시겠습니까?")
            builder.setPositiveButton("확인"){ dialog, which ->
                logout.logoutToServer()
            }
            builder.setNegativeButton("취소"){dialog, which ->

            }

            dialog = builder.create()
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }
    }

    fun back(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }
}