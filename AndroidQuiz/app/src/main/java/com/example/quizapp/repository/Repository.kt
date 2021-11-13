package com.example.quizapp.repository

import com.example.quizapp.api.Retrofitinstance
import com.example.quizapp.models.Post
import retrofit2.Response

class Repository {
    suspend fun getPost():Response<Post>{
        return Retrofitinstance.api.getPost()
    }
}