package com.example.carrotmarket.bottom01.comment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentLookViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _idResult = MutableLiveData<ArrayList<CommentListItem>>()
    val idResult : LiveData<ArrayList<CommentListItem>>
        get() = _idResult

    fun commentListfromServer(postId :Int){
        retrofit.commentList(postId).enqueue(object : Callback<ArrayList<CommentListItem>> {
            override fun onResponse(call: Call<ArrayList<CommentListItem>>, response: Response<ArrayList<CommentListItem>>) {

                if (response.isSuccessful){
                    _idResult.value = response.body()
                    Log.d("resultValue", "comment : ${response.body()}")
                }else {
                    Log.d("Fail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<CommentListItem>>, t: Throwable) {
                Log.i("retrofitResult","fail : ${(t.message).toString()}")
            }
        })
    }
}