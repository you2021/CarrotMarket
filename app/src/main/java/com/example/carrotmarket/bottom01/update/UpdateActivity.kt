package com.example.carrotmarket.bottom01.update

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateBinding
    lateinit var updateViewModel: UpdateViewModel
    lateinit var dialog: AlertDialog

    var postId = 0
    var category: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        updateViewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)
        setContentView(binding.root)

        setUpdateBefore()
        setObserver()
        UpdateToServerBtn()
        categoryBtn()


    }

    fun setUpdateBefore(){
        val tittle = intent.getStringExtra("tittle")
        val comment = intent.getStringExtra("comment")
        val price = intent.getStringExtra("price")
        val category = intent.getStringExtra("category")
        postId = intent.getIntExtra("postId", 0)

        binding.etTittle.setText(tittle)
        binding.category.setText(category)
        binding.etPrice.setText(price)
        binding.etComment.setText(comment)
    }

    private fun setObserver() {
        updateViewModel.idResult.observe(this, Observer {
            if (it != null) {
                Log.d("result", "${it}")
                finish()
            }
        })
    }

    fun UpdateToServerBtn() {
        binding.completion.setOnClickListener {
            if (binding.etComment.text.toString() != null) {
                updateViewModel.updateToServer(binding.etComment.text.toString(), category!! ,binding.etPrice.text.toString(),binding.etTittle.text.toString(), postId.toInt())
            }
        }
    }

    fun categoryBtn(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val items = arrayOf("디지털기기","생활가전","가구/인테리어","유아동","생활/가공식품","유아도서","스포츠/레저","여성잡화",
            "여성의류","남성패션/잡화","게임/취미","뷰티/미용","반려동물용품","도서/티켓/음반","식물","기타중고물품","삽니다")

        binding.choiceCategory.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                builder.setItems(items, object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, which: Int) {
                        category = items[which]
                        binding.category.text = items[which]
                    }
                })
                dialog = builder.create()
                dialog.setCanceledOnTouchOutside(true)
                dialog.show()
            }
        })
    }
}