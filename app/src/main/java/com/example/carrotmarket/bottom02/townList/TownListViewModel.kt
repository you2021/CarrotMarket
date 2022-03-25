package com.example.carrotmarket.bottom02.townList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService

class TownListViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _Result = MutableLiveData<ArrayList<Item>>()
    val Result : LiveData<ArrayList<Item>>
        get() = _Result

    fun listFromServer(){

//        _Result.value =
    }
}