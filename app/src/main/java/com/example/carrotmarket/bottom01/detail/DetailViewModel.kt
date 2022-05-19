package com.example.carrotmarket.bottom01.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.bottom01.list.PostListItem
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _idResult = MutableLiveData<PostListItem>()
    val idResult : LiveData<PostListItem>
        get() = _idResult

    fun detailFromServer(num :Int){
        retrofit.detail(num).enqueue(object : Callback<PostListItem> {
            override fun onResponse(call: Call<PostListItem>, response: Response<PostListItem>) {

                if (response.isSuccessful){
                    _idResult.value = response.body()
                    Log.d("detail", "${response.body()}")
                }else {
                    Log.d("detail Fail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<PostListItem>, t: Throwable) {
                Log.i("detail","fail : ${(t.message).toString()}")
            }
        })
    }
}