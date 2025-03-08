package com.example.homework18.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.homework18.R
import com.example.homework18.databinding.FragmentSecondBinding
import com.example.homework18.ui.MyAdapter
import com.example.homework18.ui.database.App
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val myAdapter = MyAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = myAdapter

        val photoDao = (requireActivity().application as App).db.photoDao()
        val photos = photoDao.getAll()

        lifecycleScope.launch {
            photos.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect{
                myAdapter.setData(it)
                println(" {Хранилище---- ${it.size}")
            }
        }

        binding.shotButton.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}