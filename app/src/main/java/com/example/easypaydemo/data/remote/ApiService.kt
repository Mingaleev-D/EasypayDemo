package com.example.easypaydemo.data.remote

import com.example.easypaydemo.core.dto.LoginResponse
import com.example.easypaydemo.core.dto.PaymentResponse
import com.example.easypaydemo.core.dto.UserRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * @author : Mingaleev D
 * @data : 27.11.2023
 */

interface ApiService {

   //   @POST("/login")
   //   suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
   //
   //   @GET("/payments")
   //   suspend fun getPayments(
   //       @Header("app-key") appKey: String = "12345",
   //       @Header("v") version: String = "1",
   //       @Header("token") token: String
   //   ): PaymentResponse

   //   @POST("/login")
   //   suspend fun signUp(@Body userRequest: UserRequest): Response<UserResponse>

//   @POST("login")
//   suspend fun signIn(
//       @Header("app-key") appKey: String = "12345",
//       @Header("v") version: String = "1",
//       @Body userRequest: UserRequest
//   ): LoginResponse

//   @GET("payments")
//   suspend fun getPayments(
//       @Header("app-key") appKey: String = "12345",
//       @Header("v") version: String = "1",
//       @Header("token") token: String
//   ): PaymentResponse
}