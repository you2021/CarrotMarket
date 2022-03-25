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

class QuestionDetailViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<QuestionItem>()
    val result : LiveData<QuestionItem>
        get() = _result

    fun questionDetailFromServer(num : Int){
        retrofit.questionDetail(num).enqueue(object : Callback<QuestionItem> {
            override fun onResponse(call: Call<QuestionItem>, response: Response<QuestionItem>) {

                if (response.isSuccessful){
                    _result.value = response.body()
                    Log.d("noticeDetail", "notice : ${response.body()!!}")
                }else {
                    Log.d("loginResultFail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<QuestionItem>, t: Throwable) {
                Log.i("noticeDetail","notice fail : ${(t.message).toString()}")
            }
        })
    }



}