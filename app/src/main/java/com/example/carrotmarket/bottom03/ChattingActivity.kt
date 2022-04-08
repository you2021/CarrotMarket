package com.example.carrotmarket.bottom03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.carrotmarket.databinding.ActivityChattingBinding
import io.socket.client.IO
import io.socket.emitter.Emitter
import org.json.JSONObject

class ChattingActivity : AppCompatActivity() {

    lateinit var binding : ActivityChattingBinding
    lateinit var chattingViewModel: ChattingViewModel
    lateinit var chattingInitViewModel: ChattingInitViewModel

    var adapter:ChattingAdapter ?= null
    val items = ArrayList<ChattingItem>()
    var yourId = ""
    var init = ""
    var room = ""
    var saveId = ""
    var check = false

    val socket = IO.socket("http://192.168.200.115:4000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        chattingViewModel = ViewModelProvider(this).get(ChattingViewModel::class.java)
        chattingInitViewModel = ViewModelProvider(this).get(ChattingInitViewModel::class.java)
        setContentView(binding.root)

        yourId = intent.getStringExtra("userId")!!
        init = intent.getStringExtra("init")!!
        room = intent.getStringExtra("room")!!

        val pref = this.getSharedPreferences("login", MODE_PRIVATE)
        saveId = pref!!.getString("id","")!!

        adapter = ChattingAdapter(this, items)
        binding.chattingContent.adapter = adapter

        socket.connect()

        socket.on("chatting", Emitter.Listener{
            Log.e("receive chatting","123")
            val obj = it[0] as JSONObject
            val msg = obj.getString("msg")
            val id = obj.getString("id")
            val room = obj.getString("room")
            Log.e("room : ", room)

            Handler(Looper.getMainLooper()).post {
                Log.d("chattingList", id)
                if (id == saveId) return@post
                items.add(ChattingItem(msg, id, "ooo"))
                adapter!!.notifyDataSetChanged()
                binding.chattingContent.scrollToPosition(items.size - 1)
            }
        })

        setObserver()
        setInitObserver()

        chattingInit()
        chattingList()
        chattingSet()
        sendBtn()

    }

    fun setObserver(){
        chattingViewModel.result.observe(this, {
            if(check == true) return@observe
            for(i in 0..it.size-1){
                items.add(ChattingItem(it[i].comment,it[i].id, "ooo"))
                adapter!!.notifyDataSetChanged()
            }
            check = true
        })
    }

    fun setInitObserver(){
        if (init == "init"){
            chattingInitViewModel.result.observe(this,{
                val room = "room_${yourId}"
                if (it.status == "success") socketOn(room)

            })
        }else socketOn(room)
    }

    fun socketOn(room:String){
        socket.on("connect", Emitter.Listener {
            socket.emit("init", "${room}")
        })
    }

    fun chattingSet(){
        binding.yourId.text = yourId
    }

    fun sendBtn(){
        binding.send.setOnClickListener {
            val text = binding.contentEt.text.toString()
            binding.contentEt.setText("")
            socket.emit("chatting", text, saveId)

            items.add(ChattingItem(text, saveId, "ooo"))
            adapter!!.notifyDataSetChanged()
            binding.chattingContent.scrollToPosition(items.size - 1)
        }
    }

    fun chattingInit(){
        chattingInitViewModel.chattingInitToServer(yourId)
    }

    fun chattingList(){
        if(check == true) return
        chattingViewModel.chattingFromServer(room)
    }
}