package com.example.androidmvvmsample.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

interface SafeApiCall {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResultWrapper.Success(apiCall())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorMessage = throwable.response()?.errorBody()?.string()
                        ResultWrapper.GenericError(code, errorMessage)
                    }
                    else -> ResultWrapper.UnknownError
                }
            }
        }
    }
}