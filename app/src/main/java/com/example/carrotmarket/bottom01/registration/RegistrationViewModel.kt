package com.example.carrotmarket.bottom01.registration

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

class RegistrationViewModel : ViewModel(){

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ResultItem>()
    val result : LiveData<ResultItem>
        get() = _result

    fun registrationToServer(comment:String, category: String, price:String, tittle:String, img: ArrayList<String>){

        retrofit.registration(comment, category, price, tittle, img).enqueue(object : Callback<ResultItem> {
            override fun onResponse(call: Call<ResultItem>, response: Response<ResultItem>) {

                if (response.isSuccessful){
                    _result.value = response.body()
                    Log.d("resultValue", "${response.body()}")
                }else {
                    Log.d("Fail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ResultItem>, t: Throwable) {
                Log.d("resultValue", "fail : ${t.message}")
            }
        })
    }
}