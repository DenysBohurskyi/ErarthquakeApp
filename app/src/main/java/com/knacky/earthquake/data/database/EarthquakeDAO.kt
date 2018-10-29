package com.knacky.earthquake.data.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.knacky.earthquake.data.entity.Earthquake

/**
 * Created by knacky on 28.10.2018.
 */
class EarthquakeDAO(context: Context) {

    private lateinit var database: SQLiteDatabase
    private var dbHelper: DBHelper = DBHelper(context)
    private var context: Context = context
    private var allColums = arrayOf(DBHelper.COLUMN_ID, DBHelper.COLUMN_TIME, DBHelper.COLUMN_ADDRESS, DBHelper.COLUMN_MAGNITUDE,
            DBHelper.COLUMN_LATITUDE, DBHelper.COLUMN_LONGITUDE)

    init {
        open()
    }

    fun createEarthquake(time: String, address: String, magnitude: String, longitude: String, latitude: String) {
        val values = ContentValues()
        values.put(DBHelper.COLUMN_TIME, time)
        values.put(DBHelper.COLUMN_ADDRESS, address)
        values.put(DBHelper.COLUMN_MAGNITUDE, magnitude)
        values.put(DBHelper.COLUMN_LONGITUDE, longitude)
        values.put(DBHelper.COLUMN_LATITUDE, latitude)

        database.insert(DBHelper.TABLE_EARTHQUAKE, null, values)
    }

    fun getAllEarthqquakes(): List<Earthquake> {
        var list = ArrayList<Earthquake>()

        val cursor = database.query(DBHelper.TABLE_EARTHQUAKE, allColums, null, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val earthquake = cursorToEarthquake(cursor)
                list.add(earthquake)
                cursor.moveToNext()
            }
            if (!cursor.isClosed) {
                val count = cursor.getCount()
                cursor.close()
            }
//            cursor.close()
        }
        return list
    }

    protected fun cursorToEarthquake(cursor: Cursor): Earthquake {
        val earthquake = Earthquake(cursor.getDouble(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5))
        return earthquake
    }

    fun open() {
        database = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }
}