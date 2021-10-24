package com.example.quizapp

import android.content.Context
import android.util.Log

import java.io.File

import java.io.InputStream



class QuizController (val context: Context){
    companion object{
        lateinit var QUESTIONS:ArrayList<Question>
    }
    val questions = arrayListOf<Question>()

    val input = "Queston1\n" +
            "Answer1 igen nem\n" +
            "Answer2 nem igen\n" +
            "Answer3 dehogynem\n" +
            "Answer4 de igen\n" +
            "Queston2\n" +
            "Answer1 he\n" +
            "Answer2 fdfg\n" +
            "Answer3 ds a\n" +
            "Answer4adad \n" +
            "Queston3\n" +
            "Answer1ads\n" +
            "Answer2dasd\n" +
            "Answer3ads\n" +
            "Answer4dassd\n" +
            "Queston4\n" +
            "Answer1ddddddddd\n" +
            "Answer2fffffffff\n" +
            "Answer3ggggggg\n" +
            "Answer4bbbb\n" +
            "Queston5\n" +
            "Answer1\n" +
            "Answer2\n" +
            "Answer3\n" +
            "Answer4\n" +
            "Queston6\n" +
            "Answer1\n" +
            "Answer2\n" +
            "Answer3\n" +
            "Answer4\n" +
            "Queston7\n" +
            "Answer1\n" +
            "Answer2\n" +
            "Answer3\n" +
            "Answer4\n" +
            "Queston8\n" +
            "Answer1\n" +
            "Answer2\n" +
            "Answer3\n" +
            "Answer4\n" +
            "Queston9\n" +
            "Answer1\n" +
            "Answer2\n" +
            "Answer3\n" +
            "Answer4\n" +
            "Queston10\n" +
            "Answer1\n" +
            "Answer2\n" +
            "Answer3\n" +
            "Answer4"


    init {
//        val inputStream: InputStream = File("questions.txt").inputStream()
//
//        val inputString = inputStream.bufferedReader().use { it.readText() }
//        println(inputString)
        var lines:Int = 0
        var mQuestion:String = ""
        var mAnswers = mutableListOf<String>()
        val questionLines = input.split("\n")

        questionLines.forEach {
            if (lines%5==0){
                mQuestion = it

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

