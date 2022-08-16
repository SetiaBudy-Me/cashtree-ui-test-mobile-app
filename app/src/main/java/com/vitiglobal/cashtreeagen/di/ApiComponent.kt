package com.vitiglobal.cashtreeagen.di

import com.vitiglobal.cashtreeagen.network.Api
import com.vitiglobal.cashtreeagen.view.home.HomeViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(api: Api)
    fun inject(viewModel: HomeViewModel)
}