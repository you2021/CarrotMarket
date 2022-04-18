package com.example.carrotmarket

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.util.Util
import com.example.carrotmarket.bottom01.FragmentHome
import com.example.carrotmarket.bottom02.FragmentTown
import com.example.carrotmarket.bottom03.FragmentChatting
import com.example.carrotmarket.bottom04.FragmentMyInfo
import com.example.carrotmarket.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.socket.client.IO
import io.socket.emitter.Emitter

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

        //동적퍼미션
        val permissions = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, permissions, 100)
        }

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