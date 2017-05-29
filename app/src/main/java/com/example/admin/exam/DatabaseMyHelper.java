package com.example.admin.exam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 06.04.2017.
 */

public class DatabaseMyHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Exam";
    public static final String TABLE_EXAM_1  = "Tablichka";
    public static final String TABLE_EXAM_2  = "NeTablichka";

    public static final String KEY_ID = "_id";
    public static final String KEY_STOLBEC_1 = "stolbec";
    public static final String KEY_Mozhet_Column = "NeStolbec";

    public DatabaseMyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_EXAM_1 + " ("+ KEY_ID +
        " integer primary key, " + KEY_STOLBEC_1 + " text, " + KEY_Mozhet_Column +
        " text)");

        db.execSQL("create table " + TABLE_EXAM_1 + " ("+ KEY_ID +
                " integer primary key, " + KEY_STOLBEC_1 + " text, " + KEY_Mozhet_Column +
                " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_VOCABULARY);
        onCreate(db);
    }
}
