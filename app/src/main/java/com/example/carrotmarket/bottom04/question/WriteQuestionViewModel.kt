package com.example.carrotmarket.bottom04.question

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.ResultItem
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class WriteQuestionViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ResultItem>()
    val result : LiveData<ResultItem>
        get() = _result

    fun questionToServer(tittle:String, content:String){
        retrofit.writeQuestion(tittle, content).enqueue(object : Callback<ResultItem> {
            override fun onResponse(call: Call<ResultItem>, response: Response<ResultItem>) {

                if (response.isSuccessful){
                    _result.value = response.body()
                    Log.d("loginResult", "login id : ${response.body()}")
                }else {
                    Log.d("loginResultFail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ResultItem>, t: Throwable) {
                Log.i("loginResult","fail : ${(t.message).toString()}")
            }
        })
    }



}