package com.example.quizapp.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R


class HomeFragment : Fragment() {
    lateinit var viewLayout: View
    lateinit var imageView: ImageView
    lateinit var textView: TextView
    lateinit var btnTestYourSkills:Button
    lateinit var btnReadQuestions:Button
    lateinit var btnCreateQuestions:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun initialize() {
        imageView = viewLayout.findViewById(R.id.iv_welcome)
        textView = viewLayout.findViewById(R.id.tv_enjoy)
        btnTestYourSkills = viewLayout.findViewById(R.id.btn_test_your_skills)
        btnReadQuestions = viewLayout.findViewById((R.id.btn_read_questions))
        btnCreateQuestions = viewLayout.findViewById(R.id.btn_create_questions)
    }
    private fun setListeners(){
        btnReadQuestions.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quizStartFragment)
        }
        btnCreateQuestions.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_questionAddFragment)
        }
        btnTestYourSkills.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quizStartFragment)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewLayout = inflater.inflate(R.layout.fragment_home, container, false)
        initialize()
        setListeners()
        return viewLayout
    }

}