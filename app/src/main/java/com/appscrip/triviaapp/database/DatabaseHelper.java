package com.appscrip.triviaapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TRIVIA_DATABASE = "trivia_database";
    private static final String TAG = "DatabaseHelper";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, TRIVIA_DATABASE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        creating table in the db
        String createTable = "CREATE TABLE " + DatabaseEntry.TRIVIA_TABLE + "(" + DatabaseEntry.TRIVIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseEntry.TRIVIA_USERNAME + " TEXT NOT NULL, " + DatabaseEntry.TRIVIA_CRICKETER + " TEXT NOT NULL, " +
                DatabaseEntry.TRIVIA_FLAG_COLOR + " TEXT NOT NULL, " + DatabaseEntry.TRIVIA_DATE + " TEXT NOT NULL, " + DatabaseEntry.TRIVIA_TIME + " TEXT NOT NULL " + ");";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        if there is change in DatabaseHelper class in future then update db version variable.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseEntry.TRIVIA_TABLE);
        onCreate(sqLiteDatabase);
    }

    /*method to add data into the db*/
    public boolean addTriviaData(String name, String cricketer, String flagColor, String date, String time) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseEntry.TRIVIA_USERNAME, name);
        contentValues.put(DatabaseEntry.TRIVIA_CRICKETER, cricketer);
        contentValues.put(DatabaseEntry.TRIVIA_FLAG_COLOR, flagColor);
        contentValues.put(DatabaseEntry.TRIVIA_DATE, date);
        contentValues.put(DatabaseEntry.TRIVIA_TIME, time);

        long result = sqLiteDatabase.insert(DatabaseEntry.TRIVIA_TABLE, null, contentValues);
        return result != -1;
    }

    /*method to read data from table*/
    public Cursor getTriviaData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.query(
                DatabaseEntry.TRIVIA_TABLE, null, null,null,
                null,
                null,
                DatabaseEntry.TRIVIA_ID + " DESC "
        );
    }

    public void deleteItem(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(DatabaseEntry.TRIVIA_TABLE, DatabaseEntry.TRIVIA_ID + "=" + id, null);
    }

    public void deleteTable() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from " + DatabaseEntry.TRIVIA_TABLE);
    }

    /*defines the table name and column names for a single table*/
    public static class DatabaseEntry implements BaseColumns {
        public static final String TRIVIA_TABLE = "trivia_table";
        public static final String TRIVIA_ID = "id";
        public static final String TRIVIA_USERNAME = "name";
        public static final String TRIVIA_CRICKETER = "cricketer";
        public static final String TRIVIA_FLAG_COLOR = "flag_color";
        public static final String TRIVIA_DATE = "date";
        public static final String TRIVIA_TIME = "time";
    }

}
