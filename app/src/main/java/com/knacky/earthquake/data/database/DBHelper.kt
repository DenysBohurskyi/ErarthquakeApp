package com.knacky.earthquake.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

/**
 * Created by knacky on 28.10.2018.
 */
class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_VERSION = 3
        private const val DATABASE_NAME = "Earthquakes.db"

        const val TABLE_EARTHQUAKE = "earthquake"
        const val COLUMN_ID = "earthquake_id"
        const val COLUMN_TIME = "time"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_MAGNITUDE = "magnitude"
        const val COLUMN_LATITUDE = "latitude"
        const val COLUMN_LONGITUDE = "longitude"

        private const val SQL_CREATE_TABLE_EARTHQUAKE =
                "CREATE TABLE $TABLE_EARTHQUAKE (" +
                        "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$COLUMN_TIME TEXT NOT NULL, " +
                        "$COLUMN_ADDRESS TEXT NOT NULL, " +
                        "$COLUMN_MAGNITUDE TEXT NOT NULL, " +
                        "$COLUMN_LATITUDE TEXT NOT NULL, " +
                        "$COLUMN_LONGITUDE TEXT NOT NULL); "

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_EARTHQUAKE"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_EARTHQUAKE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

}