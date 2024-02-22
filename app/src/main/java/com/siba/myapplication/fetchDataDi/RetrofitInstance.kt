package com.siba.myapplication.fetchDataDi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://sdui-module-prod.iserveu.tech/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun provideApiService(retrofit: Retrofit):DiApiService{
        return retrofit.create(DiApiService::class.java)
    }
   /* @Provides
    @Singleton
    fun providePinApiService(client: OkHttpClient): PinService {
        return Retrofit.Builder()
            .baseUrl(AppDataConstant.PINCODE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(PinService::class.java)
    }*/

}