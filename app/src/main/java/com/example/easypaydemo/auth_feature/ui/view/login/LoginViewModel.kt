package com.example.easypaydemo.auth_feature.ui.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easypaydemo.auth_feature.repository.AuthRepository
import com.example.easypaydemo.core.common.UiState
import com.example.easypaydemo.core.dto.LoginResponse
import com.example.easypaydemo.core.dto.UserRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

   private val _loginResult = MutableLiveData<UiState<LoginResponse>>()
   val loginResult: LiveData<UiState<LoginResponse>> get() = _loginResult

   fun login(userRequest: UserRequest) {
      viewModelScope.launch {

         _loginResult.postValue(UiState.Loading)
         try {
            val result = authRepository.auth(userRequest = userRequest)
            _loginResult.postValue(UiState.Success(result))
         } catch (ex: Exception) {
            _loginResult.postValue(UiState.Error(error = ex))
         }

      }
   }
}