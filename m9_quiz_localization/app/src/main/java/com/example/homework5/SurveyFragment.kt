package com.example.homework5

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homework5.databinding.FragmentSurveyBinding
import com.example.skillbox_hw_quiz.quiz.Quiz
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

        val questions: Quiz = if(binding.buttonSend.text == "Отправить") {
            QuizStorage.getQuiz(QuizStorage.Locale.Ru)
        } else {
            QuizStorage.getQuiz(QuizStorage.Locale.En)
        }

        val listOfQuestions = questions.questions

        binding.questionOne.text = listOfQuestions[0].question
        binding.answerQOne1.text = listOfQuestions[0].answers[0]
        binding.answerQOne2.text = listOfQuestions[0].answers[1]
        binding.answerQOne3.text = listOfQuestions[0].answers[2]
        binding.answerQOne4.text = listOfQuestions[0].answers[3]

        binding.questionTwo.text = listOfQuestions[1].question
        binding.answerQTwo1.text = listOfQuestions[1].answers[0]
        binding.answerQTwo2.text = listOfQuestions[1].answers[1]
        binding.answerQTwo3.text = listOfQuestions[1].answers[2]
        binding.answerQTwo4.text = listOfQuestions[1].answers[3]

        binding.questionThree.text = listOfQuestions[2].question
        binding.answerQThree1.text = listOfQuestions[2].answers[0]
        binding.answerQThree2.text = listOfQuestions[2].answers[1]
        binding.answerQThree3.text = listOfQuestions[2].answers[2]
        binding.answerQThree4.text = listOfQuestions[2].answers[3]

        val answers = mutableListOf<Int>()

        val question1 = binding.questionOne
        ObjectAnimator.ofFloat(question1,
            View.ALPHA,
            0f,
            1f
        ).apply {
            duration = 5000
            start()
        }

        val question2 = binding.questionTwo
        ObjectAnimator.ofFloat(question2,
            View.ALPHA,
            0f,
            1f
        ).apply {
            duration = 5000
            start()
        }

        val question3 = binding.questionThree
        ObjectAnimator.ofFloat(question3,
            View.ALPHA,
            0f,
            1f
        ).apply {
            duration = 5000
            start()
        }

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

        binding.buttonSend.setOnClickListener {
            val answersForSend = QuizStorage.answer(questions, answers)
            val action = SurveyFragmentDirections.actionSurveyFragmentToResultFragment(answersForSend)
            findNavController().navigate(action)
        }
    }
}