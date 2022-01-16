package com.example.taegyung.englishvocav3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TaeGyung on 2022-01-11.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ENGLISHWORDTABLE (_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
        "EWORD TEXT, KWORD TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ENGLISHWORDTABLE");
        onCreate(sqLiteDatabase);
    }

    public Cursor onSelect(SQLiteDatabase sqLiteDatabase, long page) {

        Cursor cursor = null;

        if(sqLiteDatabase != null) {

            long offset = ((page - 1) * 5);
            String sql = "SELECT * FROM ENGLISHWORDTABLE LIMIT 5 OFFSET ?";
            String[] params = {String.valueOf(offset)};

            cursor = sqLiteDatabase.rawQuery(sql, params);

        }

        return cursor;
    }

    public void onInsert(SQLiteDatabase sqLiteDatabase, String eWord, String kWord) {

        if(sqLiteDatabase != null) {
            String sql = "INSERT INTO ENGLISHWORDTABLE(_ID, EWORD, KWORD) " +
                        "VALUES(NULL, ?, ?)";
            Object[] params = {eWord, kWord};

            sqLiteDatabase.execSQL(sql, params);
        }
    }

    public void onDelete(SQLiteDatabase sqLiteDatabase, long id) {

        if(sqLiteDatabase != null) {
            String sql = "DELETE FROM ENGLISHWORDTABLE " +
                    "WHERE _ID = ?";
            Object[] params = {id};

            sqLiteDatabase.execSQL(sql, params);
        }
    }


}
