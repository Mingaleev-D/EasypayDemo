package com.example.easypaydemo.core.dto


import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String
)