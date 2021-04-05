package com.tutorial.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MainModel(
    val title: String? = null,
    val overview: String? = null
): Parcelable