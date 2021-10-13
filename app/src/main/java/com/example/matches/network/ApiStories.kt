package com.example.matches.network

import com.example.matches.model.RegisterDetails
import com.example.matches.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiStories {


    @POST("/Api/Player")
    suspend fun registerPlayer(@Body registerDetails: RegisterDetails) : RegisterResponse
}