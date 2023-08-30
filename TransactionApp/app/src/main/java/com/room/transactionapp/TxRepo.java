package com.room.transactionapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TxRepo {
    private TxDao txDao;
    private LiveData<List<TxEntity>> txList;

    public TxRepo(Application application) {
        TxDatabase txDatabase = TxDatabase.getInstance(application);
        txDao = txDatabase.txDao();
        txList = txDao.getAllData();

    }

    public void insertData(TxEntity txEntity){new InsertTask(txDao).execute(txEntity);}
    public void editData(TxEntity txEntity){new EditTask(txDao).execute(txEntity);}
    public void deleteData(TxEntity txEntity){new DeleteTask(txDao).execute(txEntity);}
    public LiveData<List<TxEntity>> getAllData(){
        return txList;
    }


    private static class InsertTask extends AsyncTask<TxEntity,Void,Void>{
        private TxDao txDao;
        public InsertTask(TxDao txDao) {
            this.txDao = txDao;
        }

        @Override
        protected Void doInBackground(TxEntity... txEntities) {
            txDao.insert(txEntities[0]);
            return null;
        }
    }

    private static class EditTask extends AsyncTask<TxEntity,Void,Void>{
        private TxDao txDao;
        public EditTask(TxDao txDao) {
            this.txDao = txDao;
        }

        @Override
        protected Void doInBackground(TxEntity... txEntities) {
            txDao.edit(txEntities[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<TxEntity,Void,Void>{
        private TxDao txDao;
        public DeleteTask(TxDao txDao) {
            this.txDao = txDao;
        }

        @Override
        protected Void doInBackground(TxEntity... txEntities) {
            txDao.delete(txEntities[0]);
            return null;
        }
    }
}
