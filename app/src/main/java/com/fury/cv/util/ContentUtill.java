package com.fury.cv.util;

import android.database.Cursor;

public class ContentUtill {
    public static String getLong(Cursor cursor) {
        return String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow("_id")));
    }

    public static String getTime(Cursor cursor, String columnName) {
        return TimeUtils.toFormattedTime(getInt(cursor, columnName));
    }

    public static int getInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
    }
}
