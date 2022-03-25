package com.example.carrotmarket.bottom04

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import com.example.carrotmarket.bottom04.notice.ManagerItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class ManagerViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ManagerItem>()
    val result : LiveData<ManagerItem>
        get() = _result

    fun noticeFromServer(){
        retrofit.manager().enqueue(object : Callback<ArrayList<ManagerItem>> {
            override fun onResponse(call: Call<ArrayList<ManagerItem>>, response: Response<ArrayList<ManagerItem>>) {

                if (response.isSuccessful){
                    _result.value = response.body()!!.get(0)
                    Log.d("manager", "manager : ${response.body()!!.get(0)}")
                }else {
                    Log.d("loginResultFail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<ManagerItem>>, t: Throwable) {
                Log.i("manager","manager fail : ${(t.message).toString()}")
            }
        })
    }



}