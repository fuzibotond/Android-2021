package com.example.quizapp.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentProfileBinding
import com.example.quizapp.models.SharedViewModel

class ProfileFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        initialize()
        setListeners()
        return binding.root
    }

    private fun initialize() {
        if (sharedViewModel.imgProfilePic.value != null){
            binding.imgProfile.setImageBitmap(sharedViewModel.imgProfilePic.value)
        }

        binding.tvPlayerName.setText("Your name: ")
        binding.playerName.setHint(sharedViewModel.name.value.toString())
        binding.highScore.setText("High score: " + sharedViewModel.getScore() + " points")
    }

    private fun setListeners(){
        binding.btnSave.setOnClickListener {
            if (binding.playerName.text.toString().isEmpty()){
                Toast.makeText(requireContext(),"Your name can't be empty!", Toast.LENGTH_SHORT).show()
            }else{
                sharedViewModel.saveName(binding.playerName.text.toString())
                Toast.makeText(requireContext(),"We saved your new name, ${sharedViewModel.name.value}!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
            }
        }
    }

}