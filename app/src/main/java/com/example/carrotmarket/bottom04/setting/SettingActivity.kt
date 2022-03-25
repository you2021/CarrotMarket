package com.example.carrotmarket.bottom04.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    lateinit var binding : ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        infoChange()

    }

    fun infoChange(){
        binding.infoChange.setOnClickListener {
            val intent = Intent(this, InfoChangeActivity::class.java)
            startActivity(intent)
        }
    }
}