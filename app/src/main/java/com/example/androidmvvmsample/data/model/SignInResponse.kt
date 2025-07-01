package com.example.androidmvvmsample.data.model


import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("token")
    val token: String
)