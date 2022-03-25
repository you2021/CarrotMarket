package com.example.carrotmarket.bottom01.update

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

class UpdateViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _idResult = MutableLiveData<ResultItem>()
    val idResult : LiveData<ResultItem>
        get() = _idResult

    fun updateToServer(comment:String, category: String, price:String, tittle:String, postId:Int){
        retrofit.listUpdate(comment, category, price, tittle, postId).enqueue(object : Callback<ResultItem> {
            override fun onResponse(call: Call<ResultItem>, response: Response<ResultItem>) {

                if (response.isSuccessful){
                    _idResult.value = response.body()
                    Log.d("resultValue", "${response.body()}")
                }else {
                    Log.d("Fail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ResultItem>, t: Throwable) {
                Log.i("retrofitResult","fail : ${(t.message).toString()}")
            }
        })
    }
}