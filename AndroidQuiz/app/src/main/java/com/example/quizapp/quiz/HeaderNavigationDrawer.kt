package com.example.quizapp.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.HeaderNavigationDrawerBinding
import com.example.quizapp.models.SharedViewModel


class HeaderNavigationDrawer : Fragment() {
    lateinit var viewLayout:View
    lateinit var tVName:TextView
    lateinit var tvSubName:TextView
    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var ivAvatar:ImageView
    private var _binding:HeaderNavigationDrawerBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HeaderNavigationDrawerBinding.inflate(inflater, container, false)
        viewLayout = binding.root
//        viewLayout = inflater.inflate(R.layout.header_navigation_drawer, container, false)

        return viewLayout
    }

}