package com.example.quizapp.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.databinding.QuizStartFragmentBinding
import com.example.quizapp.models.QuestionAdapter
import com.example.quizapp.models.SharedViewModel
import com.example.quizapp.module.Question


class QuestionListFragment : Fragment() {
    lateinit var viewLayout:View
    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var spinner: Spinner
    var languages = mutableListOf<String>()
    lateinit var adapter:QuestionAdapter
    lateinit var recycler_view : RecyclerView
    private var question = mutableListOf<Question>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLayout = inflater.inflate(R.layout.fragment_question_list, container, false)
        sharedViewModel.categories.value?.keys?.forEach {
            languages.add(it)
        }
        spinner = viewLayout.findViewById<Spinner>(R.id.planets_spinner)
        spinner?.adapter = activity?.let { ArrayAdapter(it.applicationContext, R.layout.support_simple_spinner_dropdown_item,languages ) } as SpinnerAdapter
        spinner?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                question.removeAll{true}
                question.addAll(sharedViewModel.categories.value?.get(type)!!)
                adapter.notifyDataSetChanged()

            }
        }

        question.addAll(sharedViewModel.questions.value!!)
        adapter = QuestionAdapter(sharedViewModel, question)

        recycler_view = viewLayout.findViewById(R.id.recycler_view)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)





//        recycler_view.setHasFixedSize(true)
        return viewLayout
    }




}