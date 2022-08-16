package com.vitiglobal.cashtreeagen.network

import com.vitiglobal.cashtreeagen.data.CatFact
import com.vitiglobal.cashtreeagen.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class Api {

    @Inject
    lateinit var apiService: ApiService

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCatFact(): Single<CatFact> {
        return apiService.getCatFact()
    }
}