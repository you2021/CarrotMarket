package com.example.carrotmarket.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.MainActivity
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivityLoginBinding
import com.example.carrotmarket.join.JoinActivity

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        idToServerBtn()
        btnToJoin()
    }

    private fun setObserver() {
        loginViewModel.idResult.observe(this, Observer {
            if (it.status == "success") {
                val sp = getSharedPreferences("login", MODE_PRIVATE)
                val edit = sp.edit()
                edit.putString("id",binding.idEdt.text.toString())
                edit.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "아이디와 패스워드가 맞지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun idToServerBtn() {
        binding.btn.setOnClickListener {
            // 방어코드 : Exception 방어
            if (binding.idEdt.text.toString() != null) {
                loginViewModel.idToServer(binding.idEdt.text.toString(), binding.pwEdt.text.toString())
            }
        }
    }

    fun btnToJoin(){
        binding.joinBtn.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

    }
}