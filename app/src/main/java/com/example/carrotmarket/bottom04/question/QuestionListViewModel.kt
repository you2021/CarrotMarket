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

class QuestionListViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ArrayList<QuestionItem>>()
    val result : LiveData<ArrayList<QuestionItem>>
        get() = _result

    fun getQuestionFromServer(){
        retrofit.question().enqueue(object : Callback<ArrayList<QuestionItem>> {
            override fun onResponse(call: Call<ArrayList<QuestionItem>>, response: Response<ArrayList<QuestionItem>>) {

                if (response.isSuccessful){
                    _result.value = response.body()
                    Log.d("loginResult", "login id : ${response.body()}")
                }else {
                    Log.d("loginResultFail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<QuestionItem>>, t: Throwable) {
                Log.i("loginResult","fail : ${(t.message).toString()}")
            }
        })
    }



}