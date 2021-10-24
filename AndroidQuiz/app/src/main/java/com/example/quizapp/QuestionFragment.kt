package com.example.quizapp

import android.app.AlertDialog
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class QuestionFragment:Fragment(R.layout.question_fragment) {
    val TAG = "Question Fragment"

    lateinit var viewLayout:View
    lateinit var rb1:RadioButton
    lateinit var rb2:RadioButton
    lateinit var rb3:RadioButton
    lateinit var rb4:RadioButton
    lateinit var txtViewQuestion:TextView
    lateinit var btnNext:Button
    lateinit var namePanel:TextView
    lateinit var quizController:QuizController
    lateinit var layout:RelativeLayout
    private var correct = 0
    private var wrong = 0
    private val sharedViewModel:SharedViewModel by activityViewModels()

    companion object{
        var COUNTER:Int = 0
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLayout = inflater.inflate(R.layout.question_fragment, container, false)
        val callback: OnBackPressedCallback = object: OnBackPressedCallback(true){


            override fun handleOnBackPressed() {
                handleBack()            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        initializeComponents()
//        namePanel.setText("Now playing: " + displayName)

        return viewLayout
    }

    fun initializeComponents(){
        namePanel = viewLayout.findViewById(R.id.namePanel)
        namePanel.setText("Now playing: "+sharedViewModel.name.value)
        if (activity != null){
            quizController = QuizController(requireActivity().applicationContext)
        }else{
            Log.i("Activity Check","No activity" )
        }

        layout  = viewLayout.findViewById<RelativeLayout>(R.id.layout)
        rb1 = RadioButton(this.activity)
        rb1.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        rb1.id = 0

        rb2 = RadioButton(this.activity)
        rb2.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        rb2.id = 1

        rb3 = RadioButton(this.activity)
        rb3.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        rb3.id = 2

        rb4 = RadioButton(this.activity)
        rb4.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        rb4.id = 3

        // Create RadioGroup Dynamically
        val radioGroup = RadioGroup(this.activity)
        val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(200, 700, 0, 0)
        radioGroup.layoutParams = params

        radioGroup.addView(rb1)
        radioGroup.addView(rb2)
        radioGroup.addView(rb3)
        radioGroup.addView(rb4)

        layout.addView(radioGroup)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            var text = getString(R.string.Chose)

            var answer = if (checkedId == 0) {
                    rb1.text
                } else if(checkedId == 1) {
                    rb2.text
                }else if(checkedId == 2) {
                    rb3.text
                }else  {
                    rb4.text
                }
            text += " " + answer
                    Log.i("Dynamic: ", text )
        }

        txtViewQuestion = viewLayout.findViewById(R.id.textViewQuestion)
        btnNext = viewLayout.findViewById(R.id.buttonNext)
        if (COUNTER==0) {
            initQuiz()
        }
        btnNext.setOnClickListener {
            COUNTER++
            if (COUNTER < quizController.questions.size ){
                Log.i("Counter: ", COUNTER.toString())
                startingQuiz(COUNTER)
            }else{
                Log.i("Counter END: ", COUNTER.toString())
                findNavController().navigate(R.id.action_questionFragment_to_quizEndFragment)
                COUNTER = 0
            }

        }

    }
    fun initQuiz(){
        quizController.randomizeQuestions()
        txtViewQuestion.setText( quizController.questions[0].text)
        val answer = quizController.questions[0].answers
        rb1.setText(answer[0])
        rb2.setText(answer[1])
        rb3.setText(answer[2])
        rb4.setText(answer[3])

        if (rb1.isChecked){
            sharedViewModel.incScore()
            correct++
            Log.i(TAG, "Correct")

        }else{
            Log.i(TAG, "Wrong")
            wrong++
        }

        Log.i(TAG, "First Results: \nCorrect: $correct \nWrong: $wrong")

    }
    fun startingQuiz(counter:Int){

            txtViewQuestion.setText( quizController.questions[counter].text)
            val answer = quizController.questions[counter].answers
                rb1.setText(answer[0])
                rb2.setText(answer[1])
                rb3.setText(answer[2])
                rb4.setText(answer[3])

            if (rb1.isChecked){
                sharedViewModel.incScore()
                correct++
                Log.i(TAG, "Correct")

            }else{
                Log.i(TAG, "Wrong")
                wrong++
            }

        Log.i(TAG, "Final Results: \nCorrect: $correct \nWrong: $wrong")
    }
    fun handleBack(){
        AlertDialog.Builder(this.activity)
            .setTitle("Exit!")
            .setMessage("Are you sure about that?")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("OK"){ _,_ ->
                findNavController().navigate(R.id.action_questionFragment_to_quizStartFragment)
            }
            .show()
    }
}
