package com.example.homework1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.homework1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var countPassenger = 0
        binding.numbersOfPassengers.text = countPassenger.toString()

        binding.minusButton.isEnabled = false

        binding.plusButton.setOnClickListener {
            countPassenger++
            binding.minusButton.isEnabled = true
            binding.numbersOfPassengers.text = countPassenger.toString()
            if (countPassenger >= 50) {
                binding.buttonReset.visibility = View.VISIBLE
                binding.infoAboutPassengers.text = "Свободных мест нет"
                binding.infoAboutPassengers.setTextColor(Color.RED)
            } else if (countPassenger in 1..49) {
                binding.infoAboutPassengers.text = "Осталось мест: ${50 - countPassenger}"
                binding.infoAboutPassengers.setTextColor(Color.BLUE)
            }
        }


        binding.minusButton.setOnClickListener {
            if (countPassenger > 0) {
                countPassenger--
                if (countPassenger in 1..49) {
                    binding.buttonReset.visibility = View.INVISIBLE
                    binding.infoAboutPassengers.text = "Осталось мест: ${50 - countPassenger}"
                    binding.infoAboutPassengers.setTextColor(Color.BLUE)

                }
                if (countPassenger == 0) {
                    binding.infoAboutPassengers.text = "Все места свободны"
                    binding.infoAboutPassengers.setTextColor(Color.GREEN)
                    binding.minusButton.isEnabled = false
                }
                binding.numbersOfPassengers.text = countPassenger.toString()
            }
        }

        binding.buttonReset.setOnClickListener {
            countPassenger = 0
            binding.numbersOfPassengers.text = countPassenger.toString()
            binding.buttonReset.visibility = View.INVISIBLE
            binding.infoAboutPassengers.text = "Все места свободны"
            binding.infoAboutPassengers.setTextColor(Color.GREEN)
            binding.minusButton.isEnabled = false
        }
    }
}