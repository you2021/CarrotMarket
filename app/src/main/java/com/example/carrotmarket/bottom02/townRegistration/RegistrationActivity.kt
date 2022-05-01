package com.example.carrotmarket.bottom02.townRegistration

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.databinding.ActivityRegistration2Binding

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegistration2Binding
    lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistration2Binding.inflate(layoutInflater)
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        completion()
        typeBtn()
    }

    private fun setObserver() {
        registrationViewModel.result.observe(this, Observer {
            if (it.status == "success") {
                finish()
            }
        })
    }

    fun completion(){
        binding.completion.setOnClickListener {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
            registrationViewModel.registrationToServer(binding.category.text.toString(), binding.etContents.text.toString())
        }
    }

    fun typeBtn() {
        var dialog: AlertDialog? = null
        var category: String? = null
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val items = arrayOf(
            "동네질문", "동네맛집", "동내소식", "취미생활", "분실/실종센터", "동네사건사고", "해주세요", "일상",
            "일상", "고양이", "건강", "살림", "인테리어", "교육/학원", "동네사진전", "출산/육아", "기타"
        )  // db

        binding.choice.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                builder.setItems(items, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, which: Int) {
                        category = items[which]
                        binding.category.text = items[which]
                    }
                })
                dialog = builder.create()
                dialog!!.setCanceledOnTouchOutside(true)
                dialog!!.show()
            }
        })
    }
}