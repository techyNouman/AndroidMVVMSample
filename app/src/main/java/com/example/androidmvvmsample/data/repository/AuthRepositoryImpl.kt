package com.example.androidmvvmsample.data.repository

import com.example.androidmvvmsample.data.model.SignInRequest
import com.example.androidmvvmsample.data.model.SignInResponse
import com.example.androidmvvmsample.data.remote.NetworkService
import com.example.androidmvvmsample.domain.repository.AuthRepository
import com.example.androidmvvmsample.utils.ResultWrapper
import com.example.androidmvvmsample.utils.SafeApiCall
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: NetworkService
) : AuthRepository, SafeApiCall {
    override suspend fun signIn(req: SignInRequest): ResultWrapper<SignInResponse> {
        return safeApiCall { api.signIn(req) }
    }
}