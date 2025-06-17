package com.example.androidmvvmsample.domain.repository

import com.example.androidmvvmsample.data.model.SignInRequest
import com.example.androidmvvmsample.data.model.SignInResponse
import com.example.androidmvvmsample.utils.ResultWrapper

interface AuthRepository {
    suspend fun signIn(req: SignInRequest): ResultWrapper<SignInResponse>
}