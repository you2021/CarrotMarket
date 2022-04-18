package com.example.carrotmarket.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.MainActivity
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivityLoginBinding
import com.example.carrotmarket.join.JoinActivity
import com.example.carrotmarket.join.JoinViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel
    lateinit var joinViewModel: JoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        joinViewModel = ViewModelProvider(this).get(JoinViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        idToServerBtn()
        btnToJoin()
        clickKakao()
    }

    private fun setObserver() {
        loginViewModel.idResult.observe(this, Observer {  // 일반 로그인
            if (it.status == "success") {
                getSharedPreferences("login", MODE_PRIVATE).edit().putString("id",it.id).apply()
                intentNext()
            }else{
                Toast.makeText(this, "아이디와 패스워드가 맞지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        })
        joinViewModel.result.observe(this, {  // 카카오 로그인 
            if (it.status == "success") {
                getSharedPreferences("login", MODE_PRIVATE).edit().putString("id",it.id).apply()
                Log.d("loginCheck","${it.id}")
                intentNext()
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

    fun getKakaoUserInto(token: OAuthToken){
        UserApiClient.instance.me { user, error ->
            if (error != null) Log.i("KakaoError", error.toString())
            else{
                val id = user!!.id.toString()
                val profile = user.kakaoAccount?.profile?.thumbnailImageUrl  // 프로필 이미지
                val name = user.kakaoAccount?.profile?.nickname!!

                joinViewModel.joinInfoToServer(id, "", name)


                Log.i("loginCheck", "$id , $name, $profile")
            }
        }
    }

    fun clickKakao() {
        binding.kakaoLogin.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){

                UserApiClient.instance.loginWithKakaoTalk(this){ token, error ->
                    getKakaoUserInto(token!!)
                }
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this){ token, error ->
                    getKakaoUserInto(token!!)
                }
            }
        }
    }

    fun intentNext(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}