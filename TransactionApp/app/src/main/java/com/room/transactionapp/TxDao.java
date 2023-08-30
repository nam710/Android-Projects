package com.room.transactionapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TxDao {
    @Insert
    public void insert(TxEntity txEntity);
    @Update
    public void edit(TxEntity txEntity);
    @Delete
    public void delete(TxEntity txEntity);
    @Query("SELECT * FROM `transaction`")
    public LiveData<List<TxEntity>> getAllData();

}
