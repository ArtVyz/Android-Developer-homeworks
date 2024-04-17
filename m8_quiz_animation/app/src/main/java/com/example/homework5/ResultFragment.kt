package com.example.homework5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homework5.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    val args: ResultFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val answers = args.MyArg

        binding.feedback.text = answers
        binding.feedback.animate().apply {
            duration = 3000
            translationY(100f)
            interpolator = AccelerateInterpolator()
        }.start()

        binding.heading.animate().apply {
            duration = 3000
            translationY(100f)
            interpolator = AccelerateInterpolator()
        }.start()

        binding.buttonStartAgain.setOnClickListener {

            findNavController().navigate(R.id.action_resultFragment_to_surveyFragment)
        }

    }
}