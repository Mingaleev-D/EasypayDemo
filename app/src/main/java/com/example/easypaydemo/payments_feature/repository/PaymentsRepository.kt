package com.example.easypaydemo.payments_feature.repository

import com.example.easypaydemo.payments_feature.data.remote.PaymentsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 29.11.2023
 */

class PaymentsRepository @Inject constructor(
    private val paymentsService: PaymentsService
) {

   suspend fun getPayments(token: String) = withContext(Dispatchers.IO) {
      paymentsService.getPayments(token = token)
   }
}