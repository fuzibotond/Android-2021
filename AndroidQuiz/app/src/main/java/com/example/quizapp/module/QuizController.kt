package com.example.quizapp.module

import android.content.Context
import com.example.quizapp.R
import java.io.BufferedReader

import java.io.InputStream
import java.io.InputStreamReader


class QuizController (val context: Context){
    companion object{
        lateinit var QUESTIONS:ArrayList<Question>
    }
    val questions = arrayListOf<Question>()

    init {

        val `is`: InputStream = context.resources.openRawResource(R.raw.questions)
        val reader = BufferedReader(InputStreamReader(`is`))

        var lines:Int = 0
        var mQuestion:String = ""
        var mAnswers = mutableListOf<String>()

        reader.forEachLine {
            if (lines%5==0){
                mQuestion = it
                println("Question $lines :"+ it)
            }else {
                mAnswers.add(it)
            }
            if (mAnswers.size == 4){
                val temp = mutableListOf<String>()
                mAnswers.forEach { temp.add(it) }
                questions.add(Question(mQuestion,temp ))
                mAnswers.clear()
            }
            lines++
        }
        QUESTIONS = this.questions
//        questions.forEach{println(it)}
    }

    fun doQuiz(quantity:Int):Unit{
        randomizeQuestions()
        var countDown = quantity
        var correct = 0
        var wrong = 0
        while (countDown != 0){
            println(questions[countDown].text)
            questions[countDown].answers.shuffled().forEach { println(it) }
            var answer = readLine()
            if (answer.toString() == "1" ){
                println("Correct")
                println()
                correct++
            }else{
                println("Wrong")
                println()
                wrong++
            }
            countDown--
        }
        println("Final Results: \nCorrect: $correct \nWrong: $wrong")
    }
    fun randomizeQuestions():Unit{
        questions.shuffle()
    }
    fun destroy(){
        this.questions.clear()
    }
}

