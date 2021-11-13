package com.example.quizapp.models

import android.app.Activity
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.MainViewModel
import com.example.quizapp.module.Question
import com.example.quizapp.module.QuizController

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
    private var _categories = MutableLiveData<MutableMap<String,MutableList<Question>>>()
    val categories = _categories


    fun saveDetailsQuestion(question:Question){
        _detailsQuestion.value = question
    }
    fun getDetailsQuestion():Question{
        return detailsQuestion.value!!
    }
    fun saveSize(size:Int){
        questionNum.value = size
    }
    fun getSize():Int{
        return _questionNum.value!!
    }
    fun getScore():Int{
        return score.value!!
    }
    fun saveScore(value:Int){
        if (value > score.value!!){
            _score.value = value
        }
    }
    fun getName():String{
        return name.value.toString()
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
    fun deleteQuestion(position:Int) {
        _questions.value?.removeAt(position)
    }
    fun saveQuizController(context: Activity){
//        if (_quizController.value == null){
//            val newQuizController = QuizController(context)
//            _quizController.value = newQuizController
//        }
//        setQuizController()
    }
    fun setQuizController(){

        //quizController.value?.questions?.let { saveQuestions(it) }
        //quizController.value?.randomizeQuestions()
    }
    fun collectQuestions(question: Question){
        _questions.value?.add(question)
    }

    fun saveProfilePic(newPicture:Bitmap?){
        _imgProfilePic.value = newPicture
    }
    fun getQuestions(): ArrayList<Question> {
        return _questions.value!!
    }
    fun addNewQuestion(newQuestion:Question, context: Activity){
        if (quizController.value == null){
//            saveQuizController(context)

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
        //questions.value?.let { saveSize(it.size) }
    }

    fun getQuestion(counterCreate: Int): Question? {
        return questions.value?.get(counterCreate)
    }
    fun saveCategories(categoriesries:MutableMap<String,MutableList<Question>>){
        _categories.value = categoriesries
    }
}