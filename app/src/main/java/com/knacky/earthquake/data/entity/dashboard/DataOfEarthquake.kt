package com.knacky.earthquake.data.entity.dashboard

/**
 * Created by knacky on 27.10.2018.
 */
data class DataOfEarthquake(
        val metadata: Metadata,
        val features: List<Feature>
)