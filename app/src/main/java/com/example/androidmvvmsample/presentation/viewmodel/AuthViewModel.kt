package com.example.androidmvvmsample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmvvmsample.data.model.SignInRequest
import com.example.androidmvvmsample.data.model.SignInResponse
import com.example.androidmvvmsample.domain.usecase.SignInUseCase
import com.example.androidmvvmsample.utils.ResultWrapper
import com.example.androidmvvmsample.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _signInResult = MutableLiveData<UIState<SignInResponse>>()
    val signInResult: LiveData<UIState<SignInResponse>> get() = _signInResult

    fun signIn(req: SignInRequest) = viewModelScope.launch {
        _signInResult.value = UIState.Loading
        when (val result = signInUseCase(req)) {
            is ResultWrapper.Success -> _signInResult.value = UIState.Success(result.data)
            is ResultWrapper.GenericError -> _signInResult.value =
                UIState.Error("Error ${result.code}: ${result.error}")
            is ResultWrapper.NetworkError -> _signInResult.value = UIState.Error("No internet connection.")
            is ResultWrapper.UnknownError -> _signInResult.value = UIState.Error("Unexpected error occurred.")
        }
    }
}