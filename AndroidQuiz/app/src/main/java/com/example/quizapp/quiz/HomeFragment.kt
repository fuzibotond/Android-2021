package com.example.quizapp.quiz

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentHomeBinding
import com.example.quizapp.models.SharedViewModel


class HomeFragment : Fragment() {
    private val sharedViewModel:SharedViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initialize() {
        sharedViewModel.saveQuizController(requireActivity())
        val icon = BitmapFactory.decodeResource(this.resources, R.mipmap.img_avatar)
        sharedViewModel.saveProfilePic(icon)

    }
    private fun setListeners(){
        binding.btnReadQuestions.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_questionListFragment)
        }
        binding.btnCreateQuestions.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_questionAddFragment)
        }
        binding.btnTestYourSkills.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quizStartFragment)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initialize()
        setListeners()
        return binding.root
    }

}