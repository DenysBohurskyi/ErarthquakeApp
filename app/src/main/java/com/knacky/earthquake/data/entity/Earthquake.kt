package com.knacky.earthquake.data.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by knacky on 28.10.2018.
 */
data class Earthquake(val time: String, val address: String, val magnitude: String, val longitude: String, val latitude: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(time)
        writeString(address)
        writeString(magnitude)
        writeString(longitude)
        writeString(latitude)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Earthquake> = object : Parcelable.Creator<Earthquake> {
            override fun createFromParcel(source: Parcel): Earthquake = Earthquake(source)
            override fun newArray(size: Int): Array<Earthquake?> = arrayOfNulls(size)
        }
    }
}