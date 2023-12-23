package com.example.homework2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myView.setTopStringText("Этот текст изменен из MainActivity")
        binding.myView.setBottomStringText("Этот текст тоже изменен из MainActivity")
        binding.myView.changeBlackSquareColor(Color.GREEN)
    }
}