package com.example.easypaydemo.payments_feature.ui.view.payments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easypaydemo.core.common.UiState
import com.example.easypaydemo.core.dto.PaymentResponse
import com.example.easypaydemo.payments_feature.repository.PaymentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val payRepository: PaymentsRepository
) : ViewModel() {

   private val _paymentsResult = MutableLiveData<UiState<PaymentResponse>>()
   val paymentsResult: LiveData<UiState<PaymentResponse>> get() = _paymentsResult

   fun getPayments(token: String) {
      viewModelScope.launch {
         _paymentsResult.postValue(UiState.Loading)
         try {
            val result = payRepository.getPayments(token = token)
            _paymentsResult.postValue(UiState.Success(result))
         } catch (ex: Exception) {
            _paymentsResult.postValue(UiState.Error(error = ex))
         }

      }
   }
}