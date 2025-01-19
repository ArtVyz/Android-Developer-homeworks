package com.example.homework17.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.homework17.R
import com.example.homework17.data.api.MyAdapter
import com.example.homework17.databinding.FragmentMainBinding
import com.example.homework17.entity.Photos
import kotlinx.coroutines.launch

class FragmentMain : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val myAdapter = MyAdapter { photos -> onItemClick(photos) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserModel()
        binding.recycler.adapter = myAdapter
        lifecycleScope.launch {
            viewModel.currentRequest.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect {
                myAdapter.setData(it)
            }
        }
    }

    fun onItemClick(item: Photos) {
        findNavController().navigate(R.id.action_main_fragment_to_fullScreenPhotoFragment)
    }
}