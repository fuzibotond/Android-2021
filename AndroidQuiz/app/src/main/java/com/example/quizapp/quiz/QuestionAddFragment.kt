package com.example.quizapp.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import com.example.quizapp.R
import com.example.quizapp.models.SharedViewModel
import com.example.quizapp.module.Question
import com.google.android.material.snackbar.Snackbar


class QuestionAddFragment : Fragment() {
    lateinit var viewLayout:View
    lateinit var imgNewQuestion:ImageView
    lateinit var etQuestionText:EditText
    lateinit var etAnswer1:EditText
    lateinit var etAnswer2:EditText
    lateinit var etAnswer3:EditText
    lateinit var etAnswer4:EditText
    lateinit var btnSaveQuestion:Button
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLayout =  inflater.inflate(R.layout.fragment_question_add, container, false)
        initialize()
        setListeners()
        return viewLayout
    }

    private fun setListeners() {

        btnSaveQuestion.setOnClickListener {

            if (etQuestionText.text.isEmpty() || etAnswer1.text.isEmpty()|| etAnswer2.text.isEmpty() ){
                val snackbar = Snackbar.make(
                    it,
                    "Filling min. two answer field and the question field is required!",
                    Snackbar.LENGTH_SHORT
                )
                snackbar.show()
            }else{
                val new_question = Question(etQuestionText.text.toString(), listOf(etAnswer1.text.toString(),
                    etAnswer2.text.toString(),
                    etAnswer3.text.toString(),
                    etAnswer4.text.toString(), ))
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
        imgNewQuestion = viewLayout.findViewById(R.id.new_question)
        etQuestionText = viewLayout.findViewById(R.id.tv_question_text)
        etAnswer1 = viewLayout.findViewById(R.id.tv_answer1)
        etAnswer2 = viewLayout.findViewById(R.id.tv_answer2)
        etAnswer3 = viewLayout.findViewById(R.id.tv_answer3)
        etAnswer4 = viewLayout.findViewById(R.id.tv_answer4)
        btnSaveQuestion = viewLayout.findViewById(R.id.btn_save_question)
    }



}