package com.example.easypaydemo.auth_feature.repository

import com.example.easypaydemo.auth_feature.data.remote.AuthService
import com.example.easypaydemo.core.dto.UserRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService
){
      suspend fun auth(userRequest: UserRequest) = withContext(Dispatchers.IO){
         authService.auth(userRequest = userRequest)
      }
}
