package com.example.carrotmarket.bottom01.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostListViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _Result = MutableLiveData<ArrayList<PostListItem>>()
    val Result : LiveData<ArrayList<PostListItem>>
        get() = _Result

    fun getListFromServer(){
        retrofit.postList().enqueue(object : Callback<ArrayList<PostListItem>> {
            override fun onResponse(call: Call<ArrayList<PostListItem>>, response: Response<ArrayList<PostListItem>>) {

//                Log.d("resultValue", "list :  ${response.body()}  : ${response.errorBody()}")
                if (response.isSuccessful){
                    _Result.value = response.body()
                    Log.d("resultValue", "list :  ${response.body()!!.size}")
                }else {
                    Log.d("success Fail : ", "list :  ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<PostListItem>>, t: Throwable) {
                Log.d("Fail : "," list : ${(t.message).toString()}")
            }
        })
    }


//    fun getErrorResponse(error : ResponseBody): ErrorRes
}