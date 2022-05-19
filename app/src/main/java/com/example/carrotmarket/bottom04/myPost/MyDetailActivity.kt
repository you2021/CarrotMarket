package com.example.carrotmarket.bottom04.myPost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom01.comment.CommentListAdapter
import com.example.carrotmarket.bottom01.comment.CommentLookViewModel
import com.example.carrotmarket.bottom01.comment.CommentRegistrationViewModel
import com.example.carrotmarket.bottom01.delete.PostDeleteViewModel
import com.example.carrotmarket.bottom01.detail.ImageAdapter
import com.example.carrotmarket.bottom01.update.UpdateActivity
import com.example.carrotmarket.databinding.ActivityMyDetailBinding
import java.util.ArrayList

class MyDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityMyDetailBinding
    lateinit var detailViewModel: MyDetailViewModel
    lateinit var commentRegistrationViewModel: CommentRegistrationViewModel
    lateinit var commentLookViewModel: CommentLookViewModel
    lateinit var deleteViewModel: PostDeleteViewModel
    lateinit var dialog: AlertDialog
    val img : ArrayList<String> = ArrayList()

    var postId = 0
    var tittle:String = ""
    var category:String = ""
    var price:String = ""
    var comment:String = ""
    var userId:String = ""
    var num = 0

    lateinit var adapter: CommentListAdapter
    lateinit var imageAdapter : ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDetailBinding.inflate(layoutInflater)
        detailViewModel = ViewModelProvider(this).get(MyDetailViewModel::class.java)
        commentRegistrationViewModel = ViewModelProvider(this).get(CommentRegistrationViewModel::class.java)
        commentLookViewModel = ViewModelProvider(this).get(CommentLookViewModel::class.java)
        deleteViewModel = ViewModelProvider(this).get(PostDeleteViewModel::class.java)
        setContentView(binding.root)
        num = intent.getIntExtra("num", 0)

        imageAdapter = ImageAdapter(this, img)
        binding.iv.adapter = imageAdapter

        setDetailObserber()
        setCommentObserber()
        setCommentListObserber()
        setDeleteObserber()

        commentBtn()
        updateBtn()
        deleteBtn()
    }

    override fun onResume() {
        super.onResume()
        detailViewModel.detailFromServer(num)
    }

    fun setDetailObserber(){
        detailViewModel.idResult.observe(this, {
            if(it.id != null ){
                binding.categoryTxt.text = it.category
                binding.priceTxt.text = "${it.price}원"
                binding.commentTxt.text = it.comment
                binding.userIdTxt.text = it.user_id
                binding.tittleTxt.text = it.tittle

                val date = it.created_dt.replace("T"," ")
                val time = date.slice(IntRange(0,18))
                binding.dateTimeTxt.text = time

                if(it.image == null){
                    img.add("")
                    imageAdapter.notifyDataSetChanged()
                }else{
                    val imgArr = it.image.split(",")
                    for(t in imgArr){
                        img.add(t)
                        imageAdapter.notifyDataSetChanged()
                    }
                }

                postId = it.id.toInt()
                comment = it.comment
                tittle = it.tittle
                category = it.category
                price = it.price
                userId = it.user_id
                commentLookViewModel.commentListfromServer(postId.toInt())
            }else Log.d("로그", "fail")


        })
    }

    fun setCommentObserber(){
        commentRegistrationViewModel.idResult.observe(this, {
            if(it != null) commentLookViewModel.commentListfromServer(postId.toInt())
        })
    }

    fun setCommentListObserber(){
        commentLookViewModel.idResult.observe(this, {
            Log.d("result", "commentList : ${it}")
            adapter = CommentListAdapter(it)
            binding.comment.adapter = adapter
        })
    }

    fun setDeleteObserber(){
        deleteViewModel.idResult.observe(this, {
            if(it != null) finish()
        })
    }

    fun commentBtn(){
        binding.btn.setOnClickListener {
            commentRegistrationViewModel.commentToServer(postId, binding.commentEdt.text.toString())
            binding.commentEdt.setText("")
            binding.commentEdt.clearFocus()
        }
    }

    fun updateBtn(){
        binding.update.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("postId", postId)
            intent.putExtra("comment", comment)
            intent.putExtra("category", category)
            intent.putExtra("price", price)
            intent.putExtra("tittle", tittle)
            startActivity(intent)
        }
    }

    fun deleteBtn(){
        binding.delet.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("삭제하시겠습니까?")
            builder.setPositiveButton("확인"){ dialog, which ->
                deleteViewModel.deleteToServer(postId.toInt())
            }
            builder.setNegativeButton("취소"){dialog, which -> finish()}

            dialog = builder.create()
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }
    }

}