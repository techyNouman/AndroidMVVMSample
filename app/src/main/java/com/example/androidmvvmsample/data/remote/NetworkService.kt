package com.example.androidmvvmsample.data.remote

import com.example.androidmvvmsample.data.model.SignInRequest
import com.example.androidmvvmsample.data.model.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkService {
    @POST("/login")
    suspend fun signIn(@Body req: SignInRequest): SignInResponse
}