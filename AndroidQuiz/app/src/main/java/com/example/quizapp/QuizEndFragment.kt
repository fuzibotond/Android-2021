package com.example.quizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class QuizEndFragment :Fragment(R.layout.quiz_end_fragment){
    lateinit var viewLayout:View
    lateinit var scoreBoard:TextView
    var displayScore:String? = ""
    lateinit var btn_try_again:Button
    private val sharedViewModel:SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLayout = inflater.inflate(R.layout.quiz_end_fragment, container, false)
        initialize()

        return viewLayout
    }

    private fun initialize() {
        scoreBoard = viewLayout.findViewById(R.id.scoreBoard)
        btn_try_again = viewLayout.findViewById(R.id.try_agin_button)
        btn_try_again.setOnClickListener {
            findNavController().navigate(R.id.action_quizEndFragment_to_quizStartFragment)
        }
        scoreBoard.setText(sharedViewModel.score.value.toString() +" / " + QuizController.QUESTIONS.size+" are correct" )
    }

}