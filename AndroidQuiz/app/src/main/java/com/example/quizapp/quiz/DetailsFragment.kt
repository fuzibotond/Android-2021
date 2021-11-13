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
import com.example.quizapp.databinding.FragmentDetailsBinding
import com.example.quizapp.databinding.FragmentHomeBinding
import com.example.quizapp.databinding.QuizStartFragmentBinding
import com.example.quizapp.models.SharedViewModel

class DetailsFragment : Fragment() {

    private val sharedViewModel:SharedViewModel by activityViewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate( inflater, container, false)
        initialize()
        setListeners()
        return binding.root
    }

    private fun initialize() {
        binding.tvQuestion.text = sharedViewModel.getDetailsQuestion().text
        binding.tvAnswer1.text = sharedViewModel.getDetailsQuestion().answers.get(0).text
        binding.tvAnswer2.text = sharedViewModel.getDetailsQuestion().answers.get(1).text
        binding.tvAnswer3.text = sharedViewModel.getDetailsQuestion().answers.get(2).text
        binding.tvAnswer4.text = sharedViewModel.getDetailsQuestion().answers.get(3).text

    }
    fun setListeners(){
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_questionListFragment)
        }
    }

}