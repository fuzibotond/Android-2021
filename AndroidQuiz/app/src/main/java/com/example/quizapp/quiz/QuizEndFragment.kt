package com.example.quizapp.quiz

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.models.SharedViewModel
import com.example.quizapp.module.QuizController

class QuizEndFragment :Fragment(R.layout.quiz_end_fragment){
    lateinit var viewLayout:View
    lateinit var scoreBoard:TextView
    lateinit var btn_try_again:Button
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLayout = inflater.inflate(R.layout.quiz_end_fragment, container, false)
        val callback: OnBackPressedCallback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                handleBack()            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        initialize()
        return viewLayout
    }

    private fun handleBack(){
        AlertDialog.Builder(this.activity)
            .setTitle("Exit!")
            .setMessage("You can't step back! Do you want to TRY AGAIN?")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Let's do it again"){ _,_ ->
                findNavController().navigate(R.id.action_quizEndFragment_to_quizStartFragment)
                QuestionFragment.COUNTER = 0
            }
            .show()
    }

    private fun initialize() {
        scoreBoard = viewLayout.findViewById(R.id.scoreBoard)
        btn_try_again = viewLayout.findViewById(R.id.try_agin_button)
        btn_try_again.setOnClickListener {
            findNavController().navigate(R.id.action_quizEndFragment_to_quizStartFragment)
        }
        scoreBoard.setText("Your score: " + sharedViewModel.score.value.toString() +" / " + sharedViewModel.questions.value?.size )
    }
}