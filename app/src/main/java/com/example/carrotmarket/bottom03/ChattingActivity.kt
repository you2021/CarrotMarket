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
    lateinit var chattingContentViewModel: ChattingContentViewModel

    var adapter:ChattingAdapter ?= null
    val items = ArrayList<ChattingItem>()
    var yourId = ""
    var roomKey = ""
    var saveId = ""
    var check = false

    val socket = IO.socket("http://192.168.200.115:4000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        chattingViewModel = ViewModelProvider(this).get(ChattingViewModel::class.java)
        chattingInitViewModel = ViewModelProvider(this).get(ChattingInitViewModel::class.java)
        chattingContentViewModel = ViewModelProvider(this).get(ChattingContentViewModel::class.java)
        setContentView(binding.root)

        yourId = intent.getStringExtra("userId").toString()
        roomKey = intent.getStringExtra("roomKey")!!
        Log.d("roomKey","activity : ${roomKey}")



        adapter = ChattingAdapter(this, items)
        binding.chattingContent.adapter = adapter

        socket.connect()

        socket.on("connect", Emitter.Listener {
            socket.emit("init", "${roomKey}")
        })

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

        chattingList()
        chattingSet()
        sendBtn()

    }

    fun setObserver(){
        chattingViewModel.result.observe(this, {   // 톡 내용 불러오기
            if(check == true) return@observe
            for(i in 0..it.size-1){
                items.add(ChattingItem(it[i].comment,it[i].id, "ooo"))
                adapter!!.notifyDataSetChanged()
            }
            check = true
        })

        chattingInitViewModel.result.observe(this,{   // 룸 정보 저장
            Log.d("room","${it.status} : ${it.code}")
        })

        chattingContentViewModel.result.observe(this,{   // 내용 저장
            val text = binding.contentEt.text.toString()
            binding.contentEt.setText("")
            if(it.status == "success") socket.emit("chatting", text )
        })
    }

    fun chattingSet(){
        binding.yourId.text = yourId
    }

    fun sendBtn(){
        binding.send.setOnClickListener {
            val text = binding.contentEt.text.toString()

            if(yourId != "") chattingInitViewModel.chattingInitToServer(yourId, roomKey)

            chattingContentViewModel.chattingContentToServer(roomKey, text)

            items.add(ChattingItem(text, saveId, "ooo"))
            adapter!!.notifyDataSetChanged()
            binding.chattingContent.scrollToPosition(items.size - 1)
        }
    }

    fun chattingList(){
        chattingViewModel.chattingFromServer(roomKey)
    }
}