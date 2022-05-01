package com.example.carrotmarket.bottom01

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom01.list.PostListAdapter
import com.example.carrotmarket.bottom01.list.PostListViewModel
import com.example.carrotmarket.databinding.FragmentHomeBinding
import com.example.carrotmarket.bottom01.registration.registrationActivity

class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var listListViewModel: PostListViewModel

    var adapter: PostListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        listListViewModel = ViewModelProvider(requireActivity()).get(PostListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObserver()
        fabBtn()
    }

    override fun onStart() {
        super.onStart()

        listListViewModel.getListFromServer()
    }

    fun setObserver(){
        listListViewModel.Result.observe(viewLifecycleOwner, Observer {
            adapter = PostListAdapter(it)

            binding.postList.adapter = adapter
        })
    }

    fun fabBtn(){
        binding.fabBtn.setOnClickListener {
            val intent = Intent(requireActivity(), registrationActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }

    companion object{
        fun newInstance(): FragmentHome {
            return FragmentHome()
        }
    }

}