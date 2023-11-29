package com.example.easypaydemo.auth_feature.data.remote

import com.example.easypaydemo.core.dto.LoginResponse
import com.example.easypaydemo.core.dto.UserRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * @author : Mingaleev D
 * @data : 29.11.2023
 */

interface AuthService {

   @POST("login")
   suspend fun auth(
       @Header("app-key") appKey: String = "12345",
       @Header("v") version: String = "1",
       @Body userRequest: UserRequest
   ): LoginResponse
}