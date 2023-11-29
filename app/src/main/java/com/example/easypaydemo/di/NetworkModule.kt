package com.example.easypaydemo.di

import com.example.easypaydemo.auth_feature.data.remote.AuthService
import com.example.easypaydemo.core.common.Constants.BASE_URL
import com.example.easypaydemo.data.remote.ApiService
import com.example.easypaydemo.payments_feature.data.remote.PaymentsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

   @Provides
   fun provideBaseUrl(): String {
      return BASE_URL
   }

   @Provides
   @Singleton
   fun provideLoggingInterceptor(): HttpLoggingInterceptor {
      return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
   }

   @Provides
   @Singleton
   fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
      val okHttpClient = OkHttpClient.Builder()
      okHttpClient.addInterceptor(logger)
      okHttpClient.callTimeout(30, TimeUnit.SECONDS)
      okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
      okHttpClient.writeTimeout(30, TimeUnit.SECONDS)
      okHttpClient.readTimeout(30, TimeUnit.SECONDS)
      val okHttp = okHttpClient.build()
      return okHttp
   }

   @Provides
   @Singleton
   fun provideConvectorFactory(): GsonConverterFactory {
      return GsonConverterFactory.create()
   }

   @Provides
   @Singleton
   fun provideRetrofit(
       baseUrl: String,
       gsonFactory: GsonConverterFactory,
       okHttpClient: OkHttpClient
   ): Retrofit {
      return Retrofit.Builder()
          .baseUrl(baseUrl)
          .addConverterFactory(gsonFactory)
          .client(okHttpClient)
          .build()
   }

   @Provides
   @Singleton
   fun provideApiAuthService(retrofit: Retrofit): AuthService {
      return retrofit.create(AuthService::class.java)
   }

   @Provides
   @Singleton
   fun provideApiPayService(retrofit: Retrofit): PaymentsService {
      return retrofit.create(PaymentsService::class.java)
   }
}