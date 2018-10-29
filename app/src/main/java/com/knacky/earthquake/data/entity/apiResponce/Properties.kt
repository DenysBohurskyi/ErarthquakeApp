package com.knacky.earthquake.data.entity.apiResponce

/**
 * Created by knacky on 27.10.2018.
 */
class Properties(
        val mag: Float,
        val place: String,
        val time: Long
) {
    override fun toString(): String {
        return "Properties(mag=$mag, place='$place', time=$time)"
    }
}