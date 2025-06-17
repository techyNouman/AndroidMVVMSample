package com.example.androidmvvmsample.domain.usecase

import com.example.androidmvvmsample.data.model.SignInRequest
import com.example.androidmvvmsample.data.model.SignInResponse
import com.example.androidmvvmsample.domain.repository.AuthRepository
import com.example.androidmvvmsample.utils.ResultWrapper
import jakarta.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(request: SignInRequest): ResultWrapper<SignInResponse> =
        repository.signIn(request)
}