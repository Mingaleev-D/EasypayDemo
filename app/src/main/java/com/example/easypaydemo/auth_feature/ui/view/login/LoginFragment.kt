package com.example.easypaydemo.auth_feature.ui.view.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.easypaydemo.R
import com.example.easypaydemo.core.common.TokenManager
import com.example.easypaydemo.core.common.UiState
import com.example.easypaydemo.core.dto.UserRequest
import com.example.easypaydemo.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

   private var _binding: FragmentLoginBinding? = null
   private val binding by lazy { _binding!! }
   private val viewModel by viewModels<LoginViewModel>()

   @Inject
   lateinit var tokenManager: TokenManager

   override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View {
      _binding = FragmentLoginBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      initObserver()


   }

   private fun initObserver() {

      viewModel.loginResult.observe(requireActivity(), Observer { result ->

         when (result) {
            is UiState.Error -> {
               binding.progressBar.isVisible = false
               Toast.makeText(requireContext(), "error try again later", Toast.LENGTH_SHORT).show()
            }

            UiState.Loading -> {
               binding.progressBar.isVisible = true
            }

            is UiState.Success -> {
               binding.progressBar.isVisible = false

               val token = result.data.response.token
               tokenManager.saveToken(token = token)
               Log.d("TAG", "initObserver: $token")
               if (tokenManager.getToken() != null) {
                  Log.d("TAG", "initObserver: ${tokenManager.getToken()}")
                  findNavController().navigate(R.id.action_loginFragment_to_paymentsFragment)
               } else {
                  Toast
                      .makeText(
                          requireContext(),
                          result?.data?.error?.errorMsg ?: "Login failed",
                          Toast.LENGTH_SHORT
                      )
                      .show()
               }
            }
         }

      })


      binding.btnLogin.setOnClickListener {
         val login = binding.edLogin.text.toString()
         val password = binding.edPassword.text.toString()

         if (login.isNotEmpty() && password.isNotEmpty()) {
            if (login == "demo" && password == "12345") {
               val loginRequest = UserRequest(login = login, password = password)
               viewModel.login(loginRequest)
            } else {
               Toast.makeText(requireContext(), "Incorrect login or password", Toast.LENGTH_SHORT).show()
            }
         } else {
            Toast.makeText(requireContext(), "Please enter login and password", Toast.LENGTH_SHORT).show()
         }

      }

   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

}

