package com.imdglobalservices.psi.database

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

import com.imdglobalservices.psi.PSIApplication

/**
 * @author Dwi Setiyono <dwi.setiyono></dwi.setiyono>@ovo.id>
 * @since 2017.15.11
 */
class PSIDatabase private constructor() : SQLiteOpenHelper(PSIApplication.context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL(PSIQuery.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.v(PSIDatabase::class.java.name,
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data")

        db.execSQL("DROP TABLE IF EXISTS " + PSIQuery.TABLE_NAME)
        onCreate(db)
    }

    companion object {
        var DATABASE_NAME = "psi.db"
        var DATABASE_VERSION = 1

        internal var instance: PSIDatabase? = null

        fun getInstance(): PSIDatabase? {
            if (instance == null) {
                synchronized(PSIDatabase::class.java) {
                    if (instance == null) {
                        instance = PSIDatabase()
                    }
                }
            }
            return instance
        }
    }
}
