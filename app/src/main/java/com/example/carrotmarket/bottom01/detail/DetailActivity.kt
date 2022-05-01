package com.example.carrotmarket.bottom01.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom01.comment.CommentRegistrationViewModel
import com.example.carrotmarket.bottom01.delete.PostDeleteViewModel
import com.example.carrotmarket.bottom01.update.UpdateActivity
import com.example.carrotmarket.bottom01.comment.CommentListAdapter
import com.example.carrotmarket.bottom01.comment.CommentLookViewModel
import com.example.carrotmarket.bottom03.ChattingActivity
import com.example.carrotmarket.databinding.ActivityDetailBinding
import java.util.ArrayList

class DetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailBinding
    lateinit var detailViewModel: DetailViewModel
    lateinit var commentRegistrationViewModel: CommentRegistrationViewModel
    lateinit var commentLookViewModel: CommentLookViewModel
    lateinit var deleteViewModel: PostDeleteViewModel
    lateinit var dialog:AlertDialog
    val img : ArrayList<String> = ArrayList()

    var postId:String = ""
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
        binding = ActivityDetailBinding.inflate(layoutInflater)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
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
        chattingBtn()
    }

    override fun onResume() {
        super.onResume()
        detailViewModel.detailFromServer(num)
    }

    fun setDetailObserber(){
        detailViewModel.idResult.observe(this, {
            binding.categoryTxt.text = it.category
            binding.dateTimeTxt.text = it.created_dt
            binding.priceTxt.text = "${it.price}원"
            binding.commentTxt.text = it.comment
            binding.userIdTxt.text = it.user_id
            binding.tittleTxt.text = it.tittle

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
            
            postId = it.id
            comment = it.comment
            tittle = it.tittle
            category = it.category
            price = it.price
            userId = it.user_id
            commentLookViewModel.commentListfromServer(postId.toInt())

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

    fun chattingBtn(){
        binding.chattingBtn.setOnClickListener {
            val intent = Intent(this, ChattingActivity::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("roomKey", postId)
            startActivity(intent)
        }
    }
}