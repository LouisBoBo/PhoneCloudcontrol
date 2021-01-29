package com.exc.applibrary.main.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Partition {

    @PrimaryKey
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
