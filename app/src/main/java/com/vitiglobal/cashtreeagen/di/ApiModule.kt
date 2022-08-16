package com.vitiglobal.cashtreeagen.di

import com.vitiglobal.cashtreeagen.network.ApiService
import com.vitiglobal.cashtreeagen.network.BaseUrl.BASE_URL
import com.vitiglobal.cashtreeagen.network.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun api(): Api {
        return Api()
    }
}