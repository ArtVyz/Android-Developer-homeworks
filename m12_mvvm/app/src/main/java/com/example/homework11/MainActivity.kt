package com.example.homework11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
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
                        progress.isVisible = viewState.isProgressVisible
                    }
                }
        }

        lifecycleScope.launch {
            viewModel.foundFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    binding.result.text =  "По запросу \"${binding.searchingEdit.text}\" ничего не найдено"
                }
        }
    }
}