package com.knacky.earthquake.extensions

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by knacky on 27.10.2018.
 */
class Utils {
    companion object {
        fun getTimeForRequest(context: Context): Pair<String, String>
        {
            val cfromCalendar = Calendar.getInstance()
            cfromCalendar.set(Calendar.HOUR_OF_DAY, 0)
            cfromCalendar.set(Calendar.MINUTE, 0)
            cfromCalendar.set(Calendar.SECOND, 0)
            cfromCalendar.set(Calendar.MILLISECOND, 0)
//            cfromCalendar.add(Calendar.DAY_OF_MONTH, -1)   //just to get  not empty data

            val cToCalendar = GregorianCalendar()
            cToCalendar.timeInMillis = System.currentTimeMillis()


            val timeFrom = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", context.resources.configuration.locale)
                    .format(cfromCalendar.time)
            val timeTo = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", context.resources.configuration.locale)
                    .format(cToCalendar.time)
            return Pair(timeFrom, timeTo)
        }
    }
}