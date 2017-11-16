package com.imdglobalservices.psi.database;

import android.content.ContentValues;

import com.google.gson.Gson;

/**
 * @author Dwi Setiyono <dwi.setiyono@ovo.id>
 * @since 2017.15.11
 */
public class PSIQuery {
    public static class Type {
        public static final String PSI_DATE = "PSI_DATE";
        public static final String PSI_DATETIME = "PSI_DATETIME";
    }

    public static final String TABLE_NAME = "LOCAL_DATA_CACHE";
    public static final String TBL_COL_NAME = "name";
    public static final String TBL_COL_DATA = "data";
    public static final String TBL_COL_TIMESTAMP = "timestamp";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + TBL_COL_NAME + " TEXT, " + TBL_COL_DATA + " BLOB, " + TBL_COL_TIMESTAMP + " INTEGER);";

    public static <T> T getDataLocalPSI(String type, java.lang.reflect.Type classType) {
        try {
            android.database.sqlite.SQLiteDatabase db = PSIDatabase.Companion.getInstance().getReadableDatabase();
            android.database.Cursor cursor = db.query(true, TABLE_NAME, new String[]{TBL_COL_DATA}, TBL_COL_NAME + "=?",
                    new String[]{type}, null, null, TBL_COL_NAME + " DESC", "1");

            T model = null;
            if (cursor != null && cursor.moveToFirst()) {
                Gson gson = new Gson();
                model = gson.fromJson(cursor.getString(cursor.getColumnIndex(TBL_COL_DATA)), classType);
            }
            cursor.close();
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <M> boolean saveDataLocalPSI(String type, M model) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(model, model.getClass());

        return insertOrUpdateDataLocalPSI(type, jsonString);
    }

    private static boolean insertOrUpdateDataLocalPSI(String type, String jsonString) {
        try {
            ContentValues values = new ContentValues();
            values.put(TBL_COL_NAME, type);
            values.put(TBL_COL_DATA, jsonString);
            values.put(TBL_COL_TIMESTAMP, System.currentTimeMillis());
            android.database.sqlite.SQLiteDatabase db = PSIDatabase.Companion.getInstance().getWritableDatabase();

            long count = db.update(TABLE_NAME, values, TBL_COL_NAME + " = ? ", new String[]{type});

            if (count > 0) {
                return true;
            } else {
                count = db.insert(TABLE_NAME, null, values);
                return (count > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void clearAllCache() {
        android.database.sqlite.SQLiteDatabase db = PSIDatabase.Companion.getInstance().getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }

    public static void clearCache(String type) {
        android.database.sqlite.SQLiteDatabase db = PSIDatabase.Companion.getInstance().getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where " + TBL_COL_NAME + " = '" + type + "'");
    }
}
