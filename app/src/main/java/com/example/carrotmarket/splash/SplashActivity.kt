package com.example.carrotmarket.splash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.MainActivity
import com.example.carrotmarket.databinding.ActivitySplashBinding
import com.example.carrotmarket.login.LoginActivity


class SplashActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashBinding
    lateinit var loginCheckViewModel: LoginCheckViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        loginCheckViewModel = ViewModelProvider(this).get(LoginCheckViewModel::class.java)
        setContentView(binding.root)

        //동적퍼미션 작업
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                requestPermissions(permissions, 0)
            }
        }

        setObserber()
        btn()
    }

    fun setObserber(){  // 자동 로그인
        loginCheckViewModel.result.observe(this, {
            Log.d("로그",it.code)

            if(it.status == "success"){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                getSharedPreferences("login", MODE_PRIVATE).edit().clear().apply()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    fun btn(){
        binding.start.setOnClickListener {
            loginCheckViewModel.loginCheckToServer(this)
        }
    }
}