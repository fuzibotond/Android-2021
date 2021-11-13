package com.example.quizapp.module

data class Question(val text:String, val answers: MutableList<Answer>) {
}
data class Answer(val text: String?, val correct:Boolean){

}
