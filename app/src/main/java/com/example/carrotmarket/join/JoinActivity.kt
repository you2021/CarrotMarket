package com.example.carrotmarket.join

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivityJoinBinding
import com.example.carrotmarket.login.LoginViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging

class JoinActivity : AppCompatActivity() {

    companion object{
        val PRIMARY_CHNNEL_ID = "channel"
        var mNotifyManager: NotificationManager? = null
    }

    lateinit var binding : ActivityJoinBinding
    lateinit var joinViewModel: JoinViewModel
    var token: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        joinViewModel = ViewModelProvider(this).get(JoinViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        joinBtn()
        locationBtn()
        fcm()
    }

    private fun setObserver() {
        joinViewModel.result.observe(this, Observer {
            if (it.status == "success") {
                getSharedPreferences("login", MODE_PRIVATE).edit().putString("id",it.id).apply()

                Log.d("loginCheck","${it.id}")
                finish()
            }else{
                Toast.makeText(this, "중복된 아이디 입니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun joinBtn(){
        if(binding.userId.text != null){
            binding.joinBtn.setOnClickListener {
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
                joinViewModel.joinInfoToServer(binding.userId.text.toString(),binding.userPw.text.toString(),binding.userName.text.toString(),)
            }
        }
    }

    fun locationBtn(){
        binding.locationBtn.setOnClickListener {
            val intent = Intent(this, LocationMapsActivity::class.java)
            startActivity(intent)
        }
    }

    fun fcm(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(object :
            OnCompleteListener<String> {
            override fun onComplete(p0: Task<String>) {
                if (!p0.isSuccessful) return

                token = p0.getResult()
                Log.d("TOKEN", token!!)

            }
        })
    }
}