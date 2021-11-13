package com.example.quizapp.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.module.Question

class QuestionAdapter(
    private val sharedViewModel:SharedViewModel,
    private var question: MutableList<Question>?
) :
    RecyclerView.Adapter<QuestionAdapter.DataViewHolder>() {



    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvQuestion: TextView
        var tvAnswer:TextView
        var tvDelete: TextView
        var tvDetails:TextView
        init {
            tvQuestion = itemView.findViewById(R.id.tv_question)
            tvAnswer = itemView.findViewById(R.id.tv_answer)
            tvDelete = itemView.findViewById(R.id.tv_delete)
            tvDetails = itemView.findViewById(R.id.tv_details)
        }
    }
    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestionAdapter.DataViewHolder, position: Int) {
        holder.tvQuestion.text = question?.get(position)?.text
        question?.get(position)?.answers?.forEach {
            if (it.correct) {
                holder.tvAnswer.text = it.text
            }
        }
        holder.tvDetails.setOnClickListener {
            question?.get(position)?.let { it1 -> sharedViewModel.saveDetailsQuestion(it1) }
            findNavController(holder.itemView).navigate(R.id.detailsFragment)
        }
        holder.tvDelete.setOnClickListener {
            sharedViewModel.deleteQuestion(position)
            question?.removeAt(position)
            this.notifyDataSetChanged()
        }
    }
    override fun getItemCount():Int {
        return question!!.size
    }

    fun setQuestion(arrayList: java.util.ArrayList<Question>?) {
        this.question = arrayList
    }

}