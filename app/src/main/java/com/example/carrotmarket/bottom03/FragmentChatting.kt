package com.example.carrotmarket.bottom03

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom01.list.PostListAdapter
import com.example.carrotmarket.bottom01.list.PostListViewModel
import com.example.carrotmarket.bottom03.chattingroom.ChattingRoomAdapter
import com.example.carrotmarket.bottom03.chattingroom.ChattingRoomViewModel
import com.example.carrotmarket.databinding.FragmentChattingBinding

class FragmentChatting : Fragment() {

    lateinit var binding:FragmentChattingBinding
    private lateinit var chattingRoomViewModel: ChattingRoomViewModel

    var adapter: ChattingRoomAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentChattingBinding.inflate(inflater, container, false)
        chattingRoomViewModel= ViewModelProvider(requireActivity()).get(ChattingRoomViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObserver()

    }

    fun setObserver(){
        chattingRoomViewModel.result.observe(viewLifecycleOwner, Observer {
            adapter = ChattingRoomAdapter(it)

            binding.chatRoom.adapter = adapter
        })
    }

    override fun onStart() {
        super.onStart()
        chattingRoomViewModel.chattingRoomFromServer()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentChatting()
    }
}