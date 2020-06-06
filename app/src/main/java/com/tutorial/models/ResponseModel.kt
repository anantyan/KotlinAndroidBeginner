package com.tutorial.models

import android.os.Parcel
import android.os.Parcelable

class ResponseModel(
    var results: List<MainModel>? = emptyList()
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(MainModel))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseModel> {
        override fun createFromParcel(parcel: Parcel): ResponseModel {
            return ResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<ResponseModel?> {
            return arrayOfNulls(size)
        }
    }
}