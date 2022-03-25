package com.example.carrotmarket.bottom04.question

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.ResultItem
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import com.example.carrotmarket.bottom02.types.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AnswerViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<AnswerItem>()
    val result : LiveData<AnswerItem>
        get() = _result

    fun answerFromServer(no : Int){
        retrofit.answer(no).enqueue(object : Callback<ArrayList<AnswerItem>> {
            override fun onResponse(call: Call<ArrayList<AnswerItem>>, response: Response<ArrayList<AnswerItem>>) {

                Log.d("answerResult", "login id : ${response.body()}")

                if (response.isSuccessful){

                    if (response.body()!!.size == 0) _result.value = AnswerItem("0","failed","00")
                    else _result.value = response.body()!!.get(0)

                }else {
                    Log.d("loginResultFail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<AnswerItem>>, t: Throwable) {
                Log.i("answerResult","fail : ${(t.message).toString()}")
            }
        })
    }



}