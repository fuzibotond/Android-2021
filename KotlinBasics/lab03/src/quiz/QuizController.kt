package quiz

import java.io.File
import java.io.InputStream
import java.util.Collections.addAll

class QuizController {
    val questions = arrayListOf<Question>()
    init {

        val inputStream: InputStream = File("questions.txt").inputStream()
        var lines:Int = 0
        var mQuestion:String = ""
        var mAnswers = mutableListOf<String>()
        inputStream.bufferedReader().forEachLine {
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
}