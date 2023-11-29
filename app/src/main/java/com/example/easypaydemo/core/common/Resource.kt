package com.example.easypaydemo.core.common


sealed class Resource <out R> {
   data class Success<out T>(val data: T): Resource<T>()
   data class Error(val error: Exception): Resource<Nothing>()
}

sealed class UiState <out R> {
   data class Success<out T>(val data: T): UiState<T>()
   data class Error(val error: Exception): UiState<Nothing>()
   object Loading: UiState<Nothing>()
}
