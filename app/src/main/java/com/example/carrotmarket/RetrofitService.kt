package com.example.carrotmarket


import com.example.carrotmarket.bottom01.comment.CommentListItem
import com.example.carrotmarket.bottom01.list.PostListItem
import com.example.carrotmarket.bottom02.townList.TownItem
import com.example.carrotmarket.bottom03.chatting.ChattingItem
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
    @POST("/loginCheck")
    fun loginCheck(@Field("key") key:String):Call<ResultItem>

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
    fun comment(@Field("postId") postId:Int, @Field("comment") comment:String):Call<ResultItem>

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

    @FormUrlEncoded
    @POST("/join/city")
    fun city(@Field("city") city:String,):Call<ResultItem>

    @FormUrlEncoded
    @POST("/kakaoLogin")
    fun kakao(@Field("ui_id") ui_id:String,@Field("ui_pw") ui_pw:String,@Field("ui_name") ui_name:String,):Call<ResultItem>

    @FormUrlEncoded
    @POST("/kakaoLogin/check")
    fun kakaoCheck(@Field("ui_id") ui_id:String,):Call<ResultItem>


    @GET("/logout")
    fun logout():Call<ResultItem>

    @FormUrlEncoded
    @POST("/change")
    fun infoChange(@Field("ui_pw") ui_pw:String, @Field("ui_name") ui_name:String,):Call<ResultItem>

    @GET("/myPost")
    fun myPost(): Call<ArrayList<PostListItem>>

    @FormUrlEncoded
    @POST("/myPost/detail")
    fun myDetail(@Field("num") num:Int):Call<PostListItem>

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
    fun socketInit(@Field("your_id") your_id:String, @Field("roomKey") roomKey:Int, ):Call<ResultItem>

    @FormUrlEncoded
    @POST("/socket/user")
    fun socketUser(@Field("roomKey") roomKey:Int, @Field("content") content:String):Call<ResultItem>

    @FormUrlEncoded
    @POST("/chattingList")
    fun chattingList(@Field("roomKey") roomKey:Int):Call<ArrayList<ChattingItem>>

    @FormUrlEncoded
    @POST("/save")
    fun save(@Field("fcmNo") fcmNo:String):Call<ResultItem>

    @FormUrlEncoded
    @POST("/townRegistration")
    fun townRegistration(@Field("type") type:String,@Field("contents") contents:String):Call<ResultItem>

    @GET("/list")
    fun list(): Call<ArrayList<TownItem>>

    @FormUrlEncoded
    @POST("/typeList")
    fun typeList(@Field("type") type:String,): Call<ArrayList<TownItem>>

    @FormUrlEncoded
    @POST("/change/updateCity")
    fun updateCity(@Field("city") city:String):Call<ResultItem>

    @FormUrlEncoded
    @POST("/favorite")
    fun favorite(@Field("postId") postId:Int, @Field("check") check:String):Call<ResultItem>

    @FormUrlEncoded
    @POST("/favorite/check")
    fun favorite_check(@Field("postId") postId:Int,):Call<ResultItem>

    @FormUrlEncoded
    @POST("/favorite/delete")
    fun favorite_delete(@Field("postId") postId:Int,):Call<ResultItem>

    @GET("/interestFavorite")
    fun interestFavorite(): Call<ArrayList<PostListItem>>

    @FormUrlEncoded
    @POST("/fcm")
    fun fcm(@Field("id") id:String,):Call<ResultItem>
}