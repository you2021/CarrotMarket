package com.example.carrotmarket.bottom04

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom04.Interest.CategoryActivity
import com.example.carrotmarket.bottom04.changeCity.changeCityActivity
import com.example.carrotmarket.bottom04.interestList.InterestListActivity
import com.example.carrotmarket.bottom04.myPost.myPostActivity
import com.example.carrotmarket.bottom04.notice.NoticeActivity
import com.example.carrotmarket.bottom04.question.QuestionListActivity
import com.example.carrotmarket.bottom04.setting.LogoutViewModel
import com.example.carrotmarket.bottom04.setting.SettingActivity
import com.example.carrotmarket.databinding.FragmentMyinfoBinding

class FragmentMyInfo : Fragment() {

    lateinit var binding: FragmentMyinfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentMyinfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.idTxt.text = requireActivity().getSharedPreferences("id", AppCompatActivity.MODE_PRIVATE).getString("myId","")!!

        settingBtn()
        myPostBtn()
        noticeBtn()
        questionListBtn()
        categoryBtn()
        changeCity()
        myListBtn()
    }

    fun changeCity(){  // 동네 변경
        binding.changeCityBtn.setOnClickListener {
            val intent = Intent(requireActivity(), changeCityActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }

    fun categoryBtn(){  // 관심카테고리
        binding.categoryBtn.setOnClickListener {
            val intent = Intent(requireActivity(), CategoryActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }


    fun settingBtn(){  // 설정
        binding.setting.setOnClickListener {
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }

    fun myListBtn(){ // 게시글
        binding.mylist.setOnClickListener {
            val intent = Intent(requireActivity(), InterestListActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }



    fun myPostBtn(){  // 게시글
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