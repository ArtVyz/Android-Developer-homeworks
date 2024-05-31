package com.example.homework11

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.homework11.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val _viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = _viewModel

        binding.mainView = viewModel

        binding.searchingEdit.addTextChangedListener {
            viewModel.searchQueryEntered(it.toString())
        }

        binding.buttonSearch.setOnClickListener {
            viewModel.searchStarted(binding.searchingEdit.text.toString())
        }

        lifecycleScope.launch {
            viewModel.viewStateFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { viewState ->
                    with(binding) {
                        buttonSearch.isEnabled = viewState.isButtonEnabled
                    }
                }
        }

//        lifecycleScope.launch {
//            viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
//                .collect { state ->
//                    when (state) {
//                        is State.Initial -> {
//                            binding.result.text = getString(R.string.start_text)
//                            binding.progress.isVisible = false
//                        }
//
//                        is State.Loading -> {
//                            binding.progress.isVisible = true
//                            binding.buttonSearch.isEnabled = false
//                        }
//
//                        is State.Success -> {
//                            binding.progress.isVisible = false
//                            binding.buttonSearch.isEnabled = true
//                            binding.result.text =
//                                "${getString(R.string.not_faunded_text_part_one)} \"${viewModel.search}\" ${getString(R.string.not_faunded_text)}"
//                        }
//                    }
//                }
//        }
    }
}