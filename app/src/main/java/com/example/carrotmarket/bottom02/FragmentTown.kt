package com.example.carrotmarket.bottom02

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.bottom01.list.PostListAdapter
import com.example.carrotmarket.bottom01.list.PostListViewModel
import com.example.carrotmarket.bottom02.townList.TownListAdapter
import com.example.carrotmarket.bottom02.townList.TownListViewModel
import com.example.carrotmarket.bottom02.townRegistration.RegistrationActivity
import com.example.carrotmarket.bottom02.types.TownTypeAdapter
import com.example.carrotmarket.bottom02.types.TownTypeViewModel
import com.example.carrotmarket.databinding.FragmentTownBinding

class FragmentTown : Fragment() {

    lateinit var binding: FragmentTownBinding
    private lateinit var townListViewModel: TownListViewModel
    private lateinit var townTypeViewModel: TownTypeViewModel

    var adapter: TownListAdapter? = null
    var typeAdapter: TownTypeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentTownBinding.inflate(inflater, container, false)
        townListViewModel = ViewModelProvider(requireActivity()).get(TownListViewModel::class.java)
        townTypeViewModel = ViewModelProvider(requireActivity()).get(TownTypeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListObserver()
        setTypeObserver()
        clickBtn()

        townTypeViewModel.type()

    }

    override fun onStart() {
        super.onStart()
        townListViewModel.listFromServer()
    }

    fun setListObserver(){
        townListViewModel.Result.observe(viewLifecycleOwner, Observer {
            adapter = TownListAdapter(it)

            binding.qaList.adapter = adapter
        })
    }

    fun setTypeObserver(){
        townTypeViewModel.result.observe(viewLifecycleOwner, Observer {
            typeAdapter = TownTypeAdapter(it)

            binding.type.adapter = typeAdapter
        })
    }

    fun clickBtn(){
        binding.fabBtn.setOnClickListener {
            val intent = Intent(requireActivity(), RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentTown()
    }
}