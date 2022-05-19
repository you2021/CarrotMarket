package com.example.carrotmarket.login

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.FCMViewModel
import com.example.carrotmarket.MainActivity
import com.example.carrotmarket.databinding.ActivityLoginBinding
import com.example.carrotmarket.join.JoinActivity
import com.example.carrotmarket.join.JoinViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {

    companion object{
        val PRIMARY_CHNNEL_ID = "channel"
        var token: String?=null
    }

    lateinit var binding : ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel
    lateinit var kakaoViewModel: KakaoViewModel

    var id :String ? = null
    var name :String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        kakaoViewModel = ViewModelProvider(this).get(KakaoViewModel::class.java)
        setContentView(binding.root)

        binding.joinBtn.setPaintFlags(binding.joinBtn.paintFlags or Paint.UNDERLINE_TEXT_FLAG)

        setObserver()
        idToServerBtn()
        btnToJoin()
        clickKakao()
    }

    private fun setObserver() {
        loginViewModel.idResult.observe(this, Observer {  // 일반 로그인
            if (it.status == "success") {
                getSharedPreferences("login", MODE_PRIVATE).edit().putString("key",it.cookie).apply()
                Log.d("cookie", it.cookie)
                intentNext()
            }else{
                Toast.makeText(this, "아이디와 패스워드가 맞지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        kakaoViewModel.result.observe(this, {
            if (it.status == "success") {
                intentNext()
            }else{
                Log.d("${it.status}", "${it.code}" )
            }
        })
    }

    fun idToServerBtn() {
        binding.btn.setOnClickListener {
            loginViewModel.idToServer(binding.idEdt.text.toString(), binding.pwEdt.text.toString())
            getSharedPreferences("id", MODE_PRIVATE).edit().putString("myId",binding.idEdt.text.toString()).apply()

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
                id = user!!.id.toString()
                val profile = user.kakaoAccount?.profile?.thumbnailImageUrl  // 프로필 이미지
                name = user.kakaoAccount?.profile?.nickname!!

                Toast.makeText(this, "$id , $name", Toast.LENGTH_SHORT).show()
                kakaoViewModel.kakaoInfoToServer(id!!, "", name!!)
                getSharedPreferences("login", MODE_PRIVATE).edit().putString("key",name).apply()


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
        val intent = Intent(this, LocationActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        ActivityCompat.finishAffinity(this)
        System.exit(0)
    }
}