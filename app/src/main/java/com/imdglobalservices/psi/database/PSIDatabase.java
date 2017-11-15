package com.imdglobalservices.psi.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.imdglobalservices.psi.PSIApplication;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class PSIDatabase extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "psi.db";
    public static int DATABASE_VERSION = 1;

    private static PSIDatabase instance = null;

    private PSIDatabase() {
        super(PSIApplication.context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static PSIDatabase getInstance() {
        if (instance == null) {
            synchronized (PSIDatabase.class) {
                if (instance == null) {
                    instance = new PSIDatabase();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(PSIQuery.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(PSIDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + PSIQuery.TABLE_NAME);
        onCreate(db);
    }
}
