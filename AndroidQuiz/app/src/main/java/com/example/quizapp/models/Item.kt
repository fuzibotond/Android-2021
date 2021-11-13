package com.example.quizapp.models

data class Item(
    val category:String,
    val type:String,
    val difficulty:String,
    val question:String,
    val correct_answer:String,
    val incorrect_answers:ArrayList<String>
) {
}