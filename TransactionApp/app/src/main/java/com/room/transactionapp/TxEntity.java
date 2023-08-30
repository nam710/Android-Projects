package com.room.transactionapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction")
public class TxEntity {
    private String title;
    private String amount;

    TxEntity(String title,String amount){
        this.title=title;
        this.amount=amount;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
