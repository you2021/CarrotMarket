package com.example.carrotmarket.bottom02.types

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import com.example.carrotmarket.bottom01.list.PostListItem
import com.example.carrotmarket.bottom02.townList.TownItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _Result = MutableLiveData<ArrayList<TownItem>>()
    val Result : LiveData<ArrayList<TownItem>>
        get() = _Result

    fun listFromServer(type : String){

        retrofit.typeList(type).enqueue(object : Callback<ArrayList<TownItem>> {
            override fun onResponse(call: Call<ArrayList<TownItem>>, response: Response<ArrayList<TownItem>>) {

//                Log.d("resultValue", "list :  ${response.body()}  : ${response.errorBody()}")
                if (response.isSuccessful){
                    _Result.value = response.body()
                    Log.d("type success", "list :  ${response.body()!!.size}")
                }else {
                    Log.d("type Fail : ", "list :  ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<TownItem>>, t: Throwable) {
                Log.d("type Fail : "," Fail : ${(t.message).toString()}")
            }
        })
    }
}