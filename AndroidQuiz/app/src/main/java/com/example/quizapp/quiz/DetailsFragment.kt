package com.example.quizapp.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.models.SharedViewModel

class DetailsFragment : Fragment() {
    lateinit var viewLayout: View
    lateinit var imgDetails: ImageView
    lateinit var tvQuestion: TextView
    lateinit var tvAnswer1:TextView
    lateinit var tvAnswer2:TextView
    lateinit var tvAnswer3:TextView
    lateinit var tvAnswer4:TextView
    lateinit var btnBack:Button
    private val sharedViewModel:SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLayout = inflater.inflate(R.layout.fragment_details, container, false)
        initialize()
        setListeners()
        return viewLayout
    }

    private fun initialize() {
        imgDetails = viewLayout.findViewById(R.id.img_details)
        tvQuestion = viewLayout.findViewById(R.id.tv_question)
        tvQuestion.text = sharedViewModel.getDetailsQuestion().text
        tvAnswer1 = viewLayout.findViewById(R.id.tv_answer1)
        tvAnswer1.text = sharedViewModel.getDetailsQuestion().answers.get(0)
        tvAnswer2 = viewLayout.findViewById(R.id.tv_answer2)
        tvAnswer2.text = sharedViewModel.getDetailsQuestion().answers.get(1)
        tvAnswer3 = viewLayout.findViewById(R.id.tv_answer3)
        tvAnswer3.text = sharedViewModel.getDetailsQuestion().answers.get(2)
        tvAnswer4 = viewLayout.findViewById(R.id.tv_answer4)
        tvAnswer4.text = sharedViewModel.getDetailsQuestion().answers.get(3)
        btnBack = viewLayout.findViewById(R.id.btn_back)

    }
    fun setListeners(){
        btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_questionListFragment)
        }
    }

}