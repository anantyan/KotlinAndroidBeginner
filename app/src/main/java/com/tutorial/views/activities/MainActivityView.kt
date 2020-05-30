package com.tutorial.views.activities

import com.tutorial.models.MainModel

interface MainActivityView {

    fun onShowLoading()
    fun onHideLoading()
    fun onResponse(response: MutableList<MainModel>)
    fun onFailure(error: Throwable)
}