package com.room.transactionapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = TxEntity.class,version = 1)
public abstract class TxDatabase extends RoomDatabase {
    public static TxDatabase instance;
    public abstract TxDao txDao();
    public static synchronized TxDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),TxDatabase.class,
                    "tx database").fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
