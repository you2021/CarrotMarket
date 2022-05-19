package com.example.carrotmarket.bottom03.chatting

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom03.FcmViewModel
import com.example.carrotmarket.databinding.ActivityChattingBinding
import io.socket.client.IO
import io.socket.emitter.Emitter
import org.json.JSONObject
import java.lang.Exception
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ChattingActivity : AppCompatActivity() {

    lateinit var binding : ActivityChattingBinding
    lateinit var chattingViewModel: ChattingViewModel
    lateinit var chattingInitViewModel: ChattingInitViewModel
    lateinit var chattingContentViewModel: ChattingContentViewModel
    private lateinit var fcmViewModel: FcmViewModel

    var adapter: ChattingAdapter?= null
    val items = ArrayList<ChattingItem>()
    var yourId = ""
    var roomKey = 0
    var saveId = ""
    var check = false
    var checkNm = 0

    val socket = IO.socket("http://192.168.200.115:80")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        chattingViewModel = ViewModelProvider(this).get(ChattingViewModel::class.java)
        chattingInitViewModel = ViewModelProvider(this).get(ChattingInitViewModel::class.java)
        chattingContentViewModel = ViewModelProvider(this).get(ChattingContentViewModel::class.java)
        fcmViewModel= ViewModelProvider(this).get(FcmViewModel::class.java)
        setContentView(binding.root)

        saveId = getSharedPreferences("id", AppCompatActivity.MODE_PRIVATE).getString("myId","")!!

        try{
            yourId = intent.getStringExtra("userId")!!
            roomKey = intent.getIntExtra("roomKey", 0)!!
            Log.d("roomKey","activity : ${roomKey}")

        }catch(e : Exception){
            Log.d("exception", e.toString())
        }


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
                val currentTime = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("HH:mm")
                items.add(ChattingItem(msg, id, currentTime.format(formatter)))
                adapter!!.notifyDataSetChanged()
                binding.chattingContent.scrollToPosition(items.size - 1)
            }
        })

        setObserver()

        chattingList()
        chattingSet()
        sendBtn()
        back()

    }
    fun setObserver(){
        chattingViewModel.result.observe(this, {   // 톡 내용 불러오기
            if(check == true) return@observe
            for(i in 0..it.size-1){
                val time = it[i].dateTime.slice(IntRange(11,15))
                Log.d("dateTime", "${it[i].dateTime} : ${time}" )
                items.add(ChattingItem(it[i].comment,it[i].id, time))
                adapter!!.notifyDataSetChanged()
            }
            checkNm = it.size
            check = true
        })

        chattingInitViewModel.result.observe(this,{   // 룸 정보 저장
            Log.d("room","${it.status} : ${it.code}")
        })

        chattingContentViewModel.result.observe(this,{   // 내용 저장
            val text = binding.contentEt.text.toString()
            binding.contentEt.setText("")
            if(it.status == "success") socket.emit("chatting", text )
            fcmViewModel.fcmFromServer(yourId)
        })
    }

    fun chattingSet(){
        binding.yourId.text = yourId
    }

    fun sendBtn(){
        binding.send.setOnClickListener {
            val text = binding.contentEt.text.toString()

            if(checkNm == 0)chattingInitViewModel.chattingInitToServer(yourId, roomKey)

            chattingContentViewModel.chattingContentToServer(roomKey, text)

            val currentTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH:mm")

            items.add(ChattingItem(text, saveId, currentTime.format(formatter)))
            adapter!!.notifyDataSetChanged()
            binding.chattingContent.scrollToPosition(items.size - 1)
        }
    }

    fun chattingList(){
        chattingViewModel.chattingFromServer(roomKey)
    }

    fun back(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }
}