package com.example.quizapp.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.quizapp.databinding.FragmentQuestionAddBinding
import com.example.quizapp.models.SharedViewModel
import com.example.quizapp.module.Question
import com.google.android.material.snackbar.Snackbar

class QuestionAddFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var _binding: FragmentQuestionAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionAddBinding.inflate(inflater, container, false)

        initialize()
        setListeners()

        return binding.root
    }

    private fun setListeners() {

        binding.btnSaveQuestion.setOnClickListener {

            if (binding.tvQuestionTextEdit.text.toString().isEmpty()  || binding.tvAnswer1.text.toString().isEmpty() || binding.tvAnswer2.text.toString().isEmpty() ){
                val snackbar = Snackbar.make(
                    it,
                    "Filling min. two answer field and the question field is required!",
                    Snackbar.LENGTH_SHORT
                )
                snackbar.show()
            }else{
                val new_question = Question(
                    binding.tvQuestionTextEdit.text.toString(), listOf(binding.tvAnswer1.text.toString(),
                    binding.tvAnswer2.text.toString(),
                    binding.tvAnswer3.text.toString(),
                    binding.tvAnswer4.text.toString(), ))
                sharedViewModel.addNewQuestion(new_question, requireActivity())
                val snackbar = Snackbar.make(
                    it,
                    "Question saved successfully! ${sharedViewModel.questions.value?.last()?.text}",
                    Snackbar.LENGTH_SHORT
                )
                snackbar.show()
            }

        }
    }

    private fun initialize() {
        binding.tvQuestionText.setEndIconOnClickListener {
            Toast.makeText(requireContext(),"Clicked", Toast.LENGTH_SHORT ).show()
            binding.tvQuestionTextEdit.setText("")
        }

    }



}