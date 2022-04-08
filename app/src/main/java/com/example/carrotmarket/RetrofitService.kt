package com.example.carrotmarket


import com.example.carrotmarket.bottom01.comment.CommentListItem
import com.example.carrotmarket.bottom01.list.PostListItem
import com.example.carrotmarket.bottom03.ChattingItem
import com.example.carrotmarket.bottom03.chattingroom.ChattingRoomItem
import com.example.carrotmarket.bottom04.notice.ManagerItem
import com.example.carrotmarket.bottom04.notice.NoticeItem
import com.example.carrotmarket.bottom04.question.AnswerItem
import com.example.carrotmarket.bottom04.question.QuestionItem
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @FormUrlEncoded
    @POST("/login")
    fun login(@Field("id") id:String,@Field("pw") pw:String,):Call<ResultItem>

    @FormUrlEncoded
    @POST("/registration")
    fun registration(@Field("comment") content:String, @Field("category") category:String, @Field("price") price:String, @Field("tittle") tittle:String, @Field("image") image: ArrayList<String>):Call<ResultItem>

    @Multipart
    @POST("/registration/thumbnail_upload")
    fun sendMediaFile(
        @Part image: ArrayList<MultipartBody.Part>,
    ): Call<java.util.ArrayList<String>>

    @GET("/getList")
    fun postList(): Call<ArrayList<PostListItem>>

    @FormUrlEncoded
    @POST("/getList/detail")
    fun detail(@Field("num") num:Int):Call<PostListItem>

    @FormUrlEncoded
    @POST("/writeComment")
    fun comment(@Field("postId") postId:String, @Field("comment") comment:String):Call<ResultItem>

    @FormUrlEncoded
    @POST("/commentList")
    fun commentList(@Field("postId") postId:Int):Call<ArrayList<CommentListItem>>

    @FormUrlEncoded
    @POST("/update")
    fun listUpdate(@Field("comment") content:String,@Field("category") category:String,@Field("price") price:String, @Field("tittle") tittle:String,@Field("postId") postId:Int):Call<ResultItem>

    @FormUrlEncoded
    @POST("/delete")
    fun listDelete(@Field("postId") postId:Int):Call<ResultItem>

    @FormUrlEncoded
    @POST("/join")
    fun join(@Field("ui_id") ui_id:String,@Field("ui_pw") ui_pw:String,@Field("ui_name") ui_name:String,):Call<ResultItem>

    @GET("/logout")
    fun logout():Call<ResultItem>

    @FormUrlEncoded
    @POST("/change")
    fun infoChange(@Field("ui_pw") ui_pw:String, @Field("ui_name") ui_name:String,):Call<ResultItem>

    @GET("/myPost")
    fun myPost(): Call<ArrayList<PostListItem>>

    @GET("/notice")
    fun notice(): Call<ArrayList<NoticeItem>>

    @GET("/manager")
    fun manager(): Call<ArrayList<ManagerItem>>

    @FormUrlEncoded
    @POST("/writeNotice")
    fun writeNotice(@Field("tittle") tittle:String, @Field("content") content:String,):Call<ResultItem>

    @FormUrlEncoded
    @POST("/notice/detail")
    fun settingDetail(@Field("num") num:Int):Call<NoticeItem>

    @GET("/question")
    fun question(): Call<ArrayList<QuestionItem>>

    @FormUrlEncoded
    @POST("/writeQuestion")
    fun writeQuestion(@Field("tittle") tittle:String, @Field("content") content:String,):Call<ResultItem>

    @FormUrlEncoded
    @POST("/question/detail")
    fun questionDetail(@Field("num") num:Int):Call<QuestionItem>

    @FormUrlEncoded
    @POST("/writeAnswer")
    fun writeAnswer(@Field("no") no:Int, @Field("content") content:String):Call<ResultItem>

    @FormUrlEncoded
    @POST("/answer")
    fun answer(@Field("no") no:Int):Call<java.util.ArrayList<AnswerItem>>

    @GET("/chatting")
    fun getRoom(): Call<ArrayList<ChattingRoomItem>>

    @FormUrlEncoded
    @POST("/socket")
    fun socketInit(@Field("your_id") your_id:String):Call<ResultItem>

    @FormUrlEncoded
    @POST("/chattingList")
    fun chattingList(@Field("room") room:String):Call<ArrayList<ChattingItem>>

}