package com.tutorial.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ResponseModel(
    val results: MutableList<MainModel>? = null
): Parcelable