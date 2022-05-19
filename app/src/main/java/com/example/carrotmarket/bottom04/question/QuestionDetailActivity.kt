package com.example.carrotmarket.bottom04.question

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom04.ManagerViewModel
import com.example.carrotmarket.databinding.ActivityQuestionDetailBinding
import java.lang.NullPointerException

class QuestionDetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityQuestionDetailBinding
    lateinit var questionDetailViewModel: QuestionDetailViewModel
    lateinit var answerViewModel: AnswerViewModel
    lateinit var managerViewModel : ManagerViewModel

    var num = 0
    var no = ""
    var check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionDetailBinding.inflate(layoutInflater)
        questionDetailViewModel = ViewModelProvider(this).get(QuestionDetailViewModel::class.java)
        managerViewModel = ViewModelProvider(this).get(ManagerViewModel::class.java)
        answerViewModel = ViewModelProvider(this).get(AnswerViewModel::class.java)
        setContentView(binding.root)

        num = intent.getIntExtra("num", 0)
        no = intent.getStringExtra("no")!!

        setDetailObserber()
        setManagerObserver()
        setAnswerObserver()
        questionDetailViewModel.questionDetailFromServer(num)
        managerViewModel.noticeFromServer()

    }

    fun setDetailObserber(){
        questionDetailViewModel.result.observe(this, {
            binding.tittle.text = it.tittle
            binding.userId.text = it.user_id
            binding.content.text = it.content

            try{
                val date = it.dateTime.replace("T"," ")
                val time = date.slice(IntRange(0,18))
                binding.dateTime.text = time
            }catch (e : NullPointerException){
                Log.d("ex", e.toString())
            }



        })
    }

    fun setManagerObserver(){
        managerViewModel.result.observe(this, {
            if(it.manager == "1"){
                check = true
            }
            answerViewModel.answerFromServer(no.toInt())
        })
    }

    fun setAnswerObserver(){
        answerViewModel.result.observe(this, {
            if(it.content == "failed"){
                if(check) {
                    binding.answer.visibility = View.VISIBLE
                    answerBtn()
                }else binding.answer.visibility = View.GONE

            }else{
                binding.answer.visibility = View.GONE
                binding.answerTxt.text = it.content
            }
        })
    }

    fun answerBtn(){
        binding.answer.setOnClickListener {
            val intent = Intent(this, WriteAnswerActivity::class.java)
            intent.putExtra("no", no)
            startActivity(intent)
        }
    }
}