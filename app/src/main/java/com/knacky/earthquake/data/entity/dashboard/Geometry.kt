package com.knacky.earthquake.data.entity.dashboard

/**
 * Created by knacky on 27.10.2018.
 */
class Geometry(
        val coordinates: List<Double>
) {
    override fun toString(): String {
        return "Geometry(coordinates=$coordinates)"
    }
}