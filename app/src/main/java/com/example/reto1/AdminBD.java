package com.example.reto1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminBD extends SQLiteOpenHelper {


    public AdminBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //codigo para crear la tabla
        db.execSQL("create table favoritos(sneakerId int primary key, sneakertitle text,sneakerDescription text, sneakerimg blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version_ant, int version_nueva) {

    }
}

