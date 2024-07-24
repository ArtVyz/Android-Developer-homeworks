package com.example.homework15

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.homework15.databinding.FragmentBlankBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class BlankFragment : Fragment() {

    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BlankViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao = (requireActivity().application as App).db.wordDao()
                return BlankViewModel(wordDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textInput.addTextChangedListener {
            viewModel.newWord.value = it.toString()
        }
        binding.buttonAdd.setOnClickListener { viewModel.buttonAdd() }
        binding.buttonClean.setOnClickListener { viewModel.buttonDelete() }

        lifecycleScope.launch {
            viewModel.allWords.collect {
                binding.textView.text = it.joinToString(
                    separator = "\r\n"
                )
            }
        }

        lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle,Lifecycle.State.STARTED)
                .collect{ state ->
                    when (state) {
                        State.INITIAL -> {

                        }

                        State.LOADING -> {

                        }

                        State.SUCCESS -> {

                        }

                        State.ERROR -> {
                            Snackbar.make(requireView(),"Ввидите корректное слово", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }
}