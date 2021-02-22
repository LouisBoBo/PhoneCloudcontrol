package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowIngListData implements Serializable {

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean implements Serializable {
        private ArrayList<String> nowPlayFileList;
        public ArrayList<String> getNowPlayFileList() {
            return nowPlayFileList;
        }
        public void setNowPlayFileList(ArrayList<String> nowPlayFileList) {
            this.nowPlayFileList = nowPlayFileList;
        }
    }
}
