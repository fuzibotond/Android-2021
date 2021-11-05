package com.example.quizapp.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.models.QuestionAdapter
import com.example.quizapp.models.SharedViewModel


class QuestionListFragment : Fragment() {
    lateinit var viewLayout:View
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLayout = inflater.inflate(R.layout.fragment_question_list, container, false)
        Log.d("xxx", "ListFragment - onCreateView")

        val adapter = QuestionAdapter(sharedViewModel)
        val recycler_view : RecyclerView = viewLayout.findViewById(R.id.recycler_view)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context )
        recycler_view.setHasFixedSize(true)
        return viewLayout
    }
    fun onItemClick(position: Int) {
//        findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        Log.d("xxx", "AdapterPosition: $position")
    }
}