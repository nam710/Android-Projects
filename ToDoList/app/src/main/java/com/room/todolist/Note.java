package com.room.todolist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "note")
public class Note {
    private String title;
    private String desc;

    public Note(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }



    @PrimaryKey(autoGenerate=true)
    private int id;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setId(int id) {
        this.id = id;
    }

}
