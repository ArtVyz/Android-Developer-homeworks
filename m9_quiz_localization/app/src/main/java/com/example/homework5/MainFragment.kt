package com.example.homework5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homework5.databinding.MainFragmentBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         binding.birthdayButton.setOnClickListener {
            val birthday = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.choose_your_birthday)
                .build()
             birthday.addOnPositiveButtonClickListener {timeInMilles ->
                 calendar.timeInMillis = timeInMilles
                 val day = calendar.get(Calendar.DAY_OF_MONTH)
                 val month = calendar.get(Calendar.MONTH) + 1
                 val year = calendar.get(Calendar.YEAR)

                 val text = "$day / $month / $year"
                 Snackbar.make(binding.birthdayButton, text, Snackbar.LENGTH_SHORT).show()
             }

             birthday.show(parentFragmentManager,"DatePicker")
        }

        binding.buttonStart.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_surveyFragment)
        }
    }
}