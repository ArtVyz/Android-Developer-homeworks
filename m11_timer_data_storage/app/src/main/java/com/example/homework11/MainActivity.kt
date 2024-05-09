package com.example.homework11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.homework11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var inputText: String
    private val repository = Repository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputText.doOnTextChanged { text, _, _, _ ->
            inputText = text.toString()
        }
        binding.buttonSave.setOnClickListener {
            if (binding.inputText.text.isNotEmpty()) {
                repository.saveText(this, inputText)
            } else {
                binding.outputText.text = "Shared Preference has no data"
            }
        }

        binding.buttonClean.setOnClickListener {
            repository.clearText(this)
        }
        binding.buttonLoadText.setOnClickListener {
            binding.outputText.text = repository.getText(this)
        }
    }
}