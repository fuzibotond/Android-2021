package com.example.quizapp.models

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.module.Question
import com.example.quizapp.module.QuizController
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class SharedViewModel : ViewModel() {
    private var _name = MutableLiveData("Sign in")
    val name: LiveData<String> = _name
    private var _score = MutableLiveData(0)
    val score:LiveData<Int> = _score
    private var _questions = MutableLiveData<ArrayList<Question>>()
    val questions = _questions
    private var _currentQuestion = MutableLiveData<Question>()
    val currentQuestion:LiveData<Question> = _currentQuestion
    private var _quizController = MutableLiveData<QuizController>()
    val quizController = _quizController
    private var _imgProfilePic = MutableLiveData<Bitmap>()
    val imgProfilePic = _imgProfilePic
    private var _questionNum = MutableLiveData(0)
    val questionNum = _questionNum
    private var _detailsQuestion = MutableLiveData<Question>()
    val detailsQuestion = _detailsQuestion

    fun saveDetailsQuestion(question:Question){
        _detailsQuestion.value = question
    }
    fun getDetailsQuestion():Question{
        return detailsQuestion.value!!
    }

    fun saveSize(){
        _questionNum.value = questions.value!!.size
    }
    fun getSize():Int{
        return questionNum.value!!
    }
    fun getScore():Int{
        return score.value!!
    }
    fun incScore(){
        _score.value = getScore()+1
    }
    fun saveName(newName:String){
        _name.value = newName
    }
    fun getCurrentQuestion(): Question? {
        return currentQuestion.value
    }
    fun saveCurrentQuestion(q:Question?){
        _currentQuestion.value = q
    }

    fun saveQuestions(newQuestion:ArrayList<Question>){
        _questions.value = newQuestion
    }
    fun saveQuizController(context: Activity){
        if (_quizController.value == null){
            val newQuizController = QuizController(context)
            _quizController.value = newQuizController
        }
        setQuizController()
    }
    fun setQuizController(){
        quizController.value?.questions?.let { saveQuestions(it) }
        quizController.value?.randomizeQuestions()
    }
    fun saveProfilePic(newPicture:Bitmap?){
        _imgProfilePic.value = newPicture
    }
    fun getQuestions(): ArrayList<Question> {
        return _questions.value!!
    }
    fun addNewQuestion(newQuestion:Question, context: Activity){
        if (quizController.value == null){
            saveQuizController(context)

            val temp = questions.value
            if (temp != null) {
                temp.add(newQuestion)
            }
            _questions.value = temp
        }
        else{
            val temp = questions.value
            if (temp != null) {
                temp.add(newQuestion)
            }
            _questions.value = temp

        }
        saveSize()
    }

    fun getQuestion(counterCreate: Int): Question? {
        return questions.value?.get(counterCreate)
    }
}