package com.example.easypaydemo.core.dto

import com.google.gson.annotations.SerializedName

/**
 * @author : Mingaleev D
 * @data : 29.11.2023
 */

data class LoginResponse(
    val success: Boolean,
    @SerializedName("response")
    val response: Response,
    val error: Error?
)

data class Response(
    @SerializedName("token")
    val token: String
)

data class Error(
    @SerializedName("error_code")
    val errorCode: Int = 0,
    @SerializedName("error_msg")
    val errorMsg: String = ""
)

data class PaymentResponse(
    val success: Boolean,
    val response: List<Payment>?,
    val error: Error?
)

data class Payment(
    @SerializedName("amount")
    val amount: Any? = null,
    @SerializedName("created")
    val created: Int? = null,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = ""
)