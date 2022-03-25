package com.example.carrotmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.carrotmarket.databinding.ActivitySplashBinding
import com.example.carrotmarket.login.LoginActivity
import io.socket.client.IO
import io.socket.emitter.Emitter
import org.json.JSONObject

class SplashActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val socket = IO.socket("http://172.30.1.46:4000")
        socket.connect()
        socket.on("connect", Emitter.Listener {
            socket.emit("init", "yujinAndYJ","yj")
        })
        socket.on("chatting", Emitter.Listener{
            Log.e("receive chatting","123")
            val obj = it[0] as JSONObject
            val msg = obj.getString("msg")
            val user_id = obj.getString("user_id")
            Log.e("receive chatting","$msg from $user_id")
            Handler(Looper.getMainLooper()).post {
                if(user_id == "yj") return@post
                Toast.makeText(this, "$msg from $user_id", Toast.LENGTH_LONG).show()
            }
        })
        binding.send.setOnClickListener {
            val text = binding.editText.text.toString()
            binding.editText.setText("")
            socket.emit("chatting", text)
        }
        Log.e("socket","socket connect")
        btn()
    }

    fun btn(){
        binding.start.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}