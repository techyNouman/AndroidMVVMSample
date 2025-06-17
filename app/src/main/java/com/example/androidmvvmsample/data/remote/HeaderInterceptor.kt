package com.example.androidmvvmsample.data.remote

import com.example.androidmvvmsample.data.local.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("accept", "application/json")
            .apply {
                sessionManager.getToken()?.let {
                    addHeader("Authorization", "Bearer $it")
                }
            }.build()
        return chain.proceed(request)
    }
}