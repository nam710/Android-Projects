package com.room.transactionapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TxViewModel extends AndroidViewModel {
    private TxRepo txRepo;
    private LiveData<List<TxEntity>> txList;
    public TxViewModel(@NonNull Application application) {
        super(application);
        txRepo = new TxRepo(application);
        txList = txRepo.getAllData();
    }

    public void insert(TxEntity txEntity){
        txRepo.insertData(txEntity);
    }
    public void edit(TxEntity txEntity){
        txRepo.editData(txEntity);
    }
    public void delete(TxEntity txEntity){
        txRepo.deleteData(txEntity);
    }

    public LiveData<List<TxEntity>> getAllNotes(){
        return txList;
    }
}
