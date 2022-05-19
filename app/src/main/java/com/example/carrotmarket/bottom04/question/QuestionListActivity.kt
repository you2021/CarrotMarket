package com.example.carrotmarket.bottom04.question

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom04.ManagerViewModel
import com.example.carrotmarket.databinding.ActivityQuestionListBinding

class QuestionListActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuestionListBinding
    lateinit var questionListViewModel: QuestionListViewModel
    lateinit var managerViewModel: ManagerViewModel

    var questionListAdapter : QuestionListAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionListBinding.inflate(layoutInflater)
        questionListViewModel = ViewModelProvider(this).get(QuestionListViewModel::class.java)
        managerViewModel = ViewModelProvider(this).get(ManagerViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        setManagerObserver()
        questionBtn()

        managerViewModel.noticeFromServer()

        back()
    }

    override fun onResume() {
        super.onResume()

        questionListViewModel.getQuestionFromServer()
    }

    fun setObserver(){
        questionListViewModel.result.observe(this, Observer {
            questionListAdapter = QuestionListAdapter(it)

            binding.questionList.adapter = questionListAdapter
        })
    }

    fun setManagerObserver(){
        managerViewModel.result.observe(this, {
            if(it.manager == "1"){
                binding.question.visibility = View.GONE
            }
        })
    }

    fun questionBtn(){
        binding.question.setOnClickListener {
            val intent = Intent(this, WriteQuestionActivity::class.java)
            startActivity(intent)
        }
    }

    fun back(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }
}