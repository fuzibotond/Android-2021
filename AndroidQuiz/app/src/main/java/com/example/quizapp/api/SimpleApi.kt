package com.example.quizapp.api

import com.example.quizapp.models.Post
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    @GET("api.php?amount=50")
    suspend fun getPost():Response<Post>
}