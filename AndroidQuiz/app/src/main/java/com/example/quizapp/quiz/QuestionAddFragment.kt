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
import com.example.quizapp.module.Answer
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
                if (!binding.ans1.isChecked && !binding.ans2.isChecked && !binding.ans3.isChecked && !binding.ans4.isChecked){
                    val snackbar = Snackbar.make(
                        it,
                        "Please select the correct answer!",
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar.show()
                }else{
                    var counter = 0
                        if (binding.ans1.isChecked){
                            counter++
                        }
                        if (binding.ans2.isChecked){
                            counter++
                        }
                        if (binding.ans3.isChecked){
                            counter++
                        }
                        if (binding.ans4.isChecked){
                            counter++
                        }
                        if (counter != 1 && counter!=0 || counter>1 ){
                            val snackbar = Snackbar.make(
                                it,
                                "You have to add only ONE correct answer!",
                                Snackbar.LENGTH_SHORT
                            )
                            snackbar.show()
                        }else{

                        val new_question = Question(
                            binding.tvQuestionTextEdit.text.toString(),
                            listOf(Answer(binding.tvAnswer1.text.toString(),binding.ans1.isChecked),
                                Answer(binding.tvAnswer2.text.toString(), binding.ans2.isChecked),
                                Answer(binding.tvAnswer3.text.toString(), binding.ans3.isChecked),
                                Answer(binding.tvAnswer4.text.toString(),binding.ans4.isChecked), ) as MutableList<Answer>
                        )
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

        }
    }

    private fun initialize() {
        binding.tvQuestionText.setEndIconOnClickListener {
            Toast.makeText(requireContext(),"Clicked", Toast.LENGTH_SHORT ).show()
            binding.tvQuestionTextEdit.setText("")
        }

    }



}