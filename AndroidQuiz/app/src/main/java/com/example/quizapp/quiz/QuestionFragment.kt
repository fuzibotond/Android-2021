package com.example.quizapp.quiz

import android.app.AlertDialog
import android.graphics.RadialGradient
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuestionAddBinding
import com.example.quizapp.databinding.QuestionFragmentBinding
import com.example.quizapp.models.SharedViewModel
import com.example.quizapp.module.QuizController
import com.google.android.material.snackbar.Snackbar

class QuestionFragment:Fragment(R.layout.question_fragment) {
    val TAG = "Question Fragment"

    lateinit var viewLayout:View
    lateinit var rb1:RadioButton
    lateinit var rb2:RadioButton
    lateinit var rb3:RadioButton
    lateinit var rb4:RadioButton
    lateinit var radioGroup:RadioGroup
    lateinit var txtViewQuestion:TextView
    lateinit var btnNext:Button
    lateinit var namePanel:TextView
    lateinit var layout:RelativeLayout
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var _binding: QuestionFragmentBinding? = null
    private val binding get() = _binding!!
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
        solveQuiz()
        if(viewLayout.isFocused){
            COUNTER = 0
        }

        return viewLayout
    }


    private fun solveQuiz() {
        namePanel.setText("Now playing: " + sharedViewModel.name.value)

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

        btnNext.setOnClickListener {

            if (COUNTER < sharedViewModel.questions.value?.size!!){
                Log.i("Counter: ", COUNTER.toString())
                startingQuiz(COUNTER)

            }else{
                Log.i("Counter END: ", COUNTER.toString())
                findNavController().navigate(R.id.action_questionFragment_to_quizEndFragment)
                COUNTER = 0
            }
            if (!rb1.isChecked && !rb2.isChecked && !rb3.isChecked && !rb4.isChecked){
                val snack = Snackbar.make(it,"Please select your answer!",Snackbar.LENGTH_LONG)
                snack.show()
                Log.i("XXX" ,"bajvan $COUNTER")

            }
            else{
                COUNTER++
            }
        }
    }

    fun initializeComponents(){
        namePanel = viewLayout.findViewById(R.id.namePanel)


        sharedViewModel.saveQuizController(requireActivity())

        txtViewQuestion = viewLayout.findViewById(R.id.textViewQuestion)
        btnNext = viewLayout.findViewById(R.id.buttonNext)
        layout  = viewLayout.findViewById(R.id.layout)

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

        radioGroup = RadioGroup(this.activity)
        val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(200, 700, 0, 0)
        radioGroup.layoutParams = params

        radioGroup.addView(rb1)
        radioGroup.addView(rb2)
        radioGroup.addView(rb3)
        radioGroup.addView(rb4)

        layout.addView(radioGroup)
        startingQuiz(COUNTER)

    }

    fun startingQuiz(counter:Int){
        sharedViewModel.saveCurrentQuestion(sharedViewModel.questions.value?.get(counter))
        txtViewQuestion.setText(sharedViewModel.currentQuestion.value?.text)
        val answer = sharedViewModel.currentQuestion.value?.answers
                rb1.setText(answer?.get(0))
                rb2.setText(answer?.get(1))
        if (answer?.get(2).toString() == ""){
            radioGroup.removeView(rb3)
        }else{
            rb3.setText(answer?.get(2))
        }
        if (answer?.get(3).toString() == ""){
            radioGroup.removeView(rb4)
        }else{
            rb4.setText(answer?.get(3))
        }

            if (rb1.isChecked){
                sharedViewModel.incScore()
            }


    }
    fun handleBack(){
        AlertDialog.Builder(this.activity)
            .setTitle("Exit!")
            .setMessage("Are you sure about that?")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("OK"){ _,_ ->
                findNavController().navigate(R.id.action_questionFragment_to_quizStartFragment)
                COUNTER = 0
            }
            .show()
    }


}
