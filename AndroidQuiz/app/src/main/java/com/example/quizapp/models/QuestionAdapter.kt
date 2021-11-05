package com.example.quizapp.models

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R



class QuestionAdapter(
    private val sharedViewModel:SharedViewModel
) :
    RecyclerView.Adapter<QuestionAdapter.DataViewHolder>() {

    var counter_create: Int = 0
    var counter_bind: Int = 0


    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init{
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition
            Log.d("xxxx", "AdapterPosition: $currentPosition")
//            listener.onItemClick(currentPosition)
        }
    }


    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        ++counter_create
        Log.d("xxx", "onCreateViewHolder: $counter_create")
        val question = sharedViewModel.getQuestion(counter_create)
        val tvQuestion: TextView = itemView.findViewById(R.id.tv_question)
        tvQuestion.text = question?.text
        val tvAnswer:TextView = itemView.findViewById(R.id.tv_answer)
        tvAnswer.text = question?.answers?.get(0)
        val tvDetails:TextView = itemView.findViewById(R.id.tv_details)
        tvDetails.setOnClickListener {
            question?.let { it1 -> sharedViewModel.saveDetailsQuestion(it1) }
            findNavController(parent.context as Activity, R.id.nav_host_fragment).navigate(R.id.detailsFragment)
            Log.d("xxx", "Details")
        }
        val tvDelete: TextView = itemView.findViewById(R.id.tv_delete)
        tvDelete.setOnClickListener {
            Log.d("xxx", "Delete")
        }
        return DataViewHolder(itemView)
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        ++counter_bind
        Log.d("xxx", "onBindViewHolder: $counter_bind")
    }

    override fun getItemCount(): Int {
       return sharedViewModel.getQuestions().size
    }


}