package com.vitiglobal.cashtreeagen.view.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vitiglobal.cashtreeagen.R
import com.vitiglobal.cashtreeagen.data.ApiResult
import com.vitiglobal.cashtreeagen.data.CatFact
import com.vitiglobal.cashtreeagen.di.DaggerApiComponent
import com.vitiglobal.cashtreeagen.network.Api
import com.vitiglobal.cashtreeagen.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class HomeViewModel : ViewModel() {
    @Inject
    lateinit var api: Api

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val tag = Constants.TAG + " " + this::class.java.simpleName
    private val disposable = CompositeDisposable()

    val catFact = MutableLiveData<CatFact>()
    val apiResult = MutableLiveData<ApiResult>()
    val onProgress = MutableLiveData(false)

    override fun onCleared() {
        super.onCleared()
        Log.d(tag, "onCleared")
        disposable.clear()
    }

    fun getFact() {
        val method = ::getFact
        val methodName = method.name

        Log.d(tag, " ")
        Log.d(tag, "METHOD: $methodName")

        onProgress.value = true
        disposable.add(
            api.getCatFact()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CatFact>() {
                    override fun onSuccess(value: CatFact) {
                        onProgress.value = false
                        catFact.value = value
                    }

                    override fun onError(e: Throwable) {
                        onProgress.value = false

                        if (e is HttpException) {
                            Log.e(tag, "$methodName - onError: ${e.response()}")
                            apiResult.value = ApiResult(errorMessage = e.response().toString())
                        } else {
                            Log.e(tag, "$methodName - onError: ${e.localizedMessage}")
                            if (e.localizedMessage?.indexOf(Constants.UNABLE_TO_RESOLVE_HOST) != -1) {
                                apiResult.value = ApiResult(error = R.string.error_http_0)
                            } else {
                                apiResult.value = ApiResult(error = R.string.error_http_500)
                            }
                        }
                    }
                })
        )
    }
}