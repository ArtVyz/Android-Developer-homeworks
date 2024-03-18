package com.example.homework5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homework5.databinding.FragmentSurveyBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage

class SurveyFragment : Fragment() {
    private var _binding: FragmentSurveyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSurveyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val qustions = QuizStorage.getQuiz(QuizStorage.Locale.Ru)
        val listOfQustions = qustions.questions

        binding.questionOne.text = listOfQustions[0].question
        binding.answerQOne1.text = listOfQustions[0].answers[0]
        binding.answerQOne2.text = listOfQustions[0].answers[1]
        binding.answerQOne3.text = listOfQustions[0].answers[2]
        binding.answerQOne4.text = listOfQustions[0].answers[3]

        binding.questionTwo.text = listOfQustions[1].question
        binding.answerQTwo1.text = listOfQustions[1].answers[0]
        binding.answerQTwo2.text = listOfQustions[1].answers[1]
        binding.answerQTwo3.text = listOfQustions[1].answers[2]
        binding.answerQTwo4.text = listOfQustions[1].answers[3]

        binding.questionThree.text = listOfQustions[2].question
        binding.answerQThree1.text = listOfQustions[2].answers[0]
        binding.answerQThree2.text = listOfQustions[2].answers[1]
        binding.answerQThree3.text = listOfQustions[2].answers[2]
        binding.answerQThree4.text = listOfQustions[2].answers[3]

        val answers = mutableListOf<Int>()

        binding.questionGroup1.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.answerQOne1 -> answers.add(0)
                R.id.answerQOne2 -> answers.add(1)
                R.id.answerQOne3 -> answers.add(2)
                R.id.answerQOne4 -> answers.add(3)
            }
        }

        binding.questionGroup2.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.answerQTwo1 -> answers.add(0)
                R.id.answerQTwo2 -> answers.add(1)
                R.id.answerQTwo3 -> answers.add(2)
                R.id.answerQTwo4 -> answers.add(3)
            }
        }

        binding.questionGroup3.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.answerQThree1 -> answers.add(0)
                R.id.answerQThree2 -> answers.add(1)
                R.id.answerQThree3 -> answers.add(2)
                R.id.answerQThree4 -> answers.add(3)
            }
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_surveyFragment_to_mainFragment)
        }

        val bundle = Bundle()

        binding.buttonSend.setOnClickListener {
//            val string = "String"
//            bundle.putString("MyArg",string)
            findNavController().navigate(R.id.action_surveyFragment_to_resultFragment)
        }
    }
}