package com.example.homework11

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.homework11.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = MainViewModel(AuthService())

        binding.searchingEdit.addTextChangedListener {
            viewModel.searchQueryEntered(it.toString())
        }

        binding.buttonSearch.setOnClickListener {
            viewModel.searchStarted()
        }

        lifecycleScope.launch {
            viewModel.viewStateFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { viewState ->
                    with(binding) {
                        buttonSearch.isEnabled = viewState.isButtonEnabled
                    }
                }
        }
        lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when (state) {
                        State.Loading -> {
                            Log.d(TAG,"State.Loading!!!")
                            binding.progress.isVisible = true
                        }

                        State.Success -> {
                            Log.d(TAG,"State.Success!!!")
                            binding.progress.isVisible = false
                        }
                    }
                }
        }

        lifecycleScope.launch {
            viewModel.foundFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    binding.result.text =
                        "По запросу \"${binding.searchingEdit.text}\" ничего не найдено"
                }
        }
    }
}