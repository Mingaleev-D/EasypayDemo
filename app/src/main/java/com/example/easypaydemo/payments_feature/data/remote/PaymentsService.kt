package com.example.easypaydemo.payments_feature.data.remote

import com.example.easypaydemo.core.dto.PaymentResponse
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * @author : Mingaleev D
 * @data : 29.11.2023
 */

interface PaymentsService {

   @GET("payments")
   suspend fun getPayments(
       @Header("app-key") appKey: String = "12345",
       @Header("v") version: String = "1",
       @Header("token") token: String
   ): PaymentResponse
}