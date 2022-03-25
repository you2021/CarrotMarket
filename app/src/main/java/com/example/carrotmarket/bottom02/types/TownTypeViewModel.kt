package com.example.carrotmarket.bottom02.types

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService

class TownTypeViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ArrayList<Item>>()
    val result : LiveData<ArrayList<Item>>
        get() = _result

    fun type(){
        _result.value = arrayListOf<Item>(Item("동네질문"), Item("동네맛집"),Item("동내소식"),Item("취미생활"),Item("분실/실종센터"),
            Item("동네사건사고"),Item("해주세요"),Item("일상"),Item("고양이"),Item("강아지"),Item("건강"),Item("살림"),
            Item("인테리어"),Item("교육/학원"),Item("동네사진전"),Item("출산/육아"),Item("기타"))
    }
}