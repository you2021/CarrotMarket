package com.example.carrotmarket.bottom04

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.MainActivity
import com.example.carrotmarket.bottom04.myPost.myPostActivity
import com.example.carrotmarket.bottom04.notice.NoticeActivity
import com.example.carrotmarket.bottom04.question.QuestionListActivity
import com.example.carrotmarket.bottom04.setting.InfoChangeActivity
import com.example.carrotmarket.bottom04.setting.SettingActivity
import com.example.carrotmarket.login.LoginViewModel
import com.example.carrotmarket.databinding.FragmentMyinfoBinding
import com.example.carrotmarket.login.LoginActivity

class FragmentMyInfo : Fragment() {

    lateinit var binding: FragmentMyinfoBinding
    lateinit var logout : LogoutViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentMyinfoBinding.inflate(inflater, container, false)
        logout = ViewModelProvider(requireActivity()).get(LogoutViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()

        logoutBtn()
        settingBtn()
        myPostBtn()
        noticeBtn()
        questionListBtn()
    }

    private fun setObserver() {
        logout.result.observe(viewLifecycleOwner, Observer {
            if (it.status == "success") {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                requireActivity().startActivity(intent)
            }
        })
    }

    fun settingBtn(){
        binding.setting.setOnClickListener {
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }

    fun logoutBtn(){
        binding.logout.setOnClickListener {
            logout.logoutToServer()
        }
    }

    fun myPostBtn(){
        binding.myPost.setOnClickListener {
            val intent = Intent(requireActivity(), myPostActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }

    fun noticeBtn(){
        binding.notice.setOnClickListener {
            val intent = Intent(requireActivity(), NoticeActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }

    fun questionListBtn(){
        binding.question.setOnClickListener {
            val intent = Intent(requireActivity(), QuestionListActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }



    companion object {
        //JVMStatic 이 뭔지 알아보기 : Google 에 "Kotlin JvmStatic" 검색
        @JvmStatic
        fun newInstance() = FragmentMyInfo()
    }

}