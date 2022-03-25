package com.example.carrotmarket.bottom01.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.ResultItem
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class registrationViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _idResult = MutableLiveData<ResultItem>()
    val idResult : LiveData<ResultItem>
        get() = _idResult

    fun registrationToServer(comment:String, category: String, price:String, tittle:String, img: File?){

        val requestBody: RequestBody = RequestBody.create(MultipartBody.FORM, img!!)

        val body: MultipartBody.Part = MultipartBody.Part.createFormData("image", "test_image", requestBody)
        retrofit.sendMediaFile(body).enqueue(
            object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    Log.e("Upload", "success" + response.body().toString())
                    Log.e("Upload", "success" + response.errorBody().toString())

                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.e("Upload error:", t.message!!)
                }
            }
        )
//        retrofit.registration(comment, category, price, tittle, img).enqueue(object : Callback<ResultItem> {
//            override fun onResponse(call: Call<ResultItem>, response: Response<ResultItem>) {
//
//                if (response.isSuccessful){
//                    _idResult.value = response.body()
//                    Log.d("resultValue", "${response.body()}")
//                }else {
//                    Log.d("Fail : ", "${response.errorBody()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ResultItem>, t: Throwable) {
//                Log.d("resultValue", "fail : ${t.message}")
//            }
//        })
    }
}