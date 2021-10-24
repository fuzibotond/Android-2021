package com.example.quizapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private var _name = MutableLiveData("Hans Christian")
    val name: LiveData<String> = _name
    private var _score = MutableLiveData<Int>(0)
    val score:LiveData<Int> = _score

    fun getScore():Int{
        return score.value!!
    }
    fun incScore(){
        _score.value = getScore()+1
    }
    fun saveName(newName:String){
        _name.value = newName
    }
}