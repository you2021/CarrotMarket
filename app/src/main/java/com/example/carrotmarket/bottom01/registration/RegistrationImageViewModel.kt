package com.example.carrotmarket.bottom01.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.ResultItem
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class RegistrationImageViewModel :ViewModel(){

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ArrayList<String>>()
    val result : LiveData<ArrayList<String>>
        get() = _result

    fun imageToServer(img: ArrayList<MultipartBody.Part>) {

        retrofit.sendMediaFile(img).enqueue(object : Callback<ArrayList<String>> {
                override fun onResponse(call: Call<ArrayList<String>>, response: Response<ArrayList<String>>
                ) {
                    if (response.isSuccessful) {
                        _result.value = response.body()
                        Log.e("Upload :", "${response.body()!!}")
                    }
                    else Log.e("Upload :", "fail : ${response.errorBody()}")

                }

                override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                    Log.e("Upload :", "error : ${t.message}")
                }
            }
        )
    }


}