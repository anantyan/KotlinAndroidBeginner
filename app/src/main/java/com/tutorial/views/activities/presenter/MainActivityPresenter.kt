package com.tutorial.views.activities.presenter

import com.tutorial.api.MainApi
import com.tutorial.models.ResponseModel
import com.tutorial.utils.RetrofitUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityPresenter(
    private var view: MainActivityView
) {
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun resultsMainActivity() {
        view.onShowLoading()
        val api: MainApi = RetrofitUtil.providerHttpAdapter().create(MainApi::class.java)
        api.discoverMovie()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ t: ResponseModel? ->
                view.onHideLoading()
                view.onResponse(t?.results!!)
            }, { t: Throwable? ->
                view.onHideLoading()
                view.onFailure(t!!)
            })
    }

    fun detachMainActivity() {
        disposable.clear()
    }
}