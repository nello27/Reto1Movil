package com.example.reto1.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SneakerDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sneaker_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SNEAKERS = "sneakers";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";

    public SneakerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_SNEAKERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_IMAGE + " BLOB,"  // Cambiar a BLOB
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT"
                + ")";
        db.execSQL(createTableQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SNEAKERS);
        onCreate(db);
    }

    public long insertSneaker(byte[] image, String name, String description) {  // Cambiar el tipo de parámetro a byte[]
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE, image);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        return db.insert(TABLE_SNEAKERS, null, values);
    }


    public Cursor getAllSneakers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_SNEAKERS, null, null, null, null, null, null);
    }
}
