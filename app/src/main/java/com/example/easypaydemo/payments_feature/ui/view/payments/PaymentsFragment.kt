package com.example.easypaydemo.payments_feature.ui.view.payments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easypaydemo.R
import com.example.easypaydemo.core.common.Constants
import com.example.easypaydemo.core.common.TokenManager
import com.example.easypaydemo.core.common.UiState
import com.example.easypaydemo.data.remote.ApiService
import com.example.easypaydemo.databinding.FragmentPaymentsBinding
import com.example.easypaydemo.auth_feature.ui.view.login.LoginViewModel
import com.example.easypaydemo.payments_feature.ui.view.payments.adapter.PaymentAdapter
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class PaymentsFragment : Fragment() {

   private var _binding: FragmentPaymentsBinding? = null
   private val binding by lazy { _binding!! }
   private val viewModel by viewModels<PaymentsViewModel>()
   private val rAdapter: PaymentAdapter = PaymentAdapter()

   @Inject
   lateinit var tokenManager: TokenManager

   override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View {
      _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      getTokent()
      initRv()
      initObserver()

      binding.imgExit.setOnClickListener {
         findNavController().navigate(R.id.action_paymentsFragment_to_loginFragment2)
      }

   }

   private fun initObserver() {

      viewModel.paymentsResult.observe(viewLifecycleOwner, Observer { result ->
         when (result) {
            is UiState.Error -> {
               binding.progressBar2.isVisible = false
               Toast.makeText(requireContext(), "error try again later", Toast.LENGTH_SHORT).show()
            }

            UiState.Loading -> {
               binding.progressBar2.isVisible = true
            }

            is UiState.Success -> {
               binding.progressBar2.isVisible = false
               val payments = result.data.response ?: emptyList()
               Log.d("PaymentAdapter", "Received ${payments.size} payments")
               rAdapter.submitList(payments)
            }
         }
      })

      val token = tokenManager.getToken()
      viewModel.getPayments(token = token!!)
   }

   private fun getTokent() {
      val sharedPreferences = requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
      val token = sharedPreferences.getString("token", "")
   }

   private fun initRv() {
      binding.rvPayments.apply {
         layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
         adapter = rAdapter
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

}