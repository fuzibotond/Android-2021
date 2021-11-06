package com.example.quizapp.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizapp.databinding.HeaderNavigationDrawerBinding


class HeaderNavigationDrawer : Fragment() {
    private var _binding:HeaderNavigationDrawerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HeaderNavigationDrawerBinding.inflate(inflater, container, false)
        Log.d("xxx", "Not contains")
        return binding.root
    }



}