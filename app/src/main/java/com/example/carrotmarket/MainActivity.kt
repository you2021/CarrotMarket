package com.example.carrotmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.carrotmarket.bottom01.FragmentHome
import com.example.carrotmarket.bottom02.FragmentTown
import com.example.carrotmarket.bottom03.FragmentChatting
import com.example.carrotmarket.bottom04.FragmentMyInfo
import com.example.carrotmarket.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.socket.client.IO
import io.socket.emitter.Emitter

// 절대로 MainActivity에서 ui 만들지 말기
// 굳이 bottom Navigation 은 괜찮
// 로그인이나 초기 화면 초기화 같은건 SlashActivity 를 별도로 만들어서 사용

class MainActivity : AppCompatActivity() {

    lateinit var binding :ActivityMainBinding

    lateinit var fragmentHome: FragmentHome
    lateinit var fragmentTown: FragmentTown
    lateinit var fragmentChatting: FragmentChatting
    lateinit var fragmentMyInfo: FragmentMyInfo

    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentManager = supportFragmentManager

        fragmentHome = FragmentHome.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container, fragmentHome).commitAllowingStateLoss()
        bottomBtn()
    }

    fun bottomBtn(){
        binding.bnv.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            val tran:FragmentTransaction = fragmentManager.beginTransaction()

            when(it.itemId) {
                R.id.bnv_home -> {
                    fragmentHome = FragmentHome.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragmentHome)
                        .commitAllowingStateLoss()
                }
                R.id.bnv_town -> {
                    fragmentTown = FragmentTown.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragmentTown)
                        .commitAllowingStateLoss()
                }
                R.id.bnv_chat -> {
                    fragmentChatting = FragmentChatting.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragmentChatting)
                        .commitAllowingStateLoss()
                }
                R.id.bnv_myInfo -> {
                    fragmentMyInfo = FragmentMyInfo.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragmentMyInfo)
                        .commitAllowingStateLoss()
                }
            }

            tran.commitAllowingStateLoss()
            true
        })
        }
}