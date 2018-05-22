package com.dd.database.sqlite.makalMatel;

/**
 * Created by dds86 on 23-Nov-17.
 */

public class ModelMakalMatel {
    String textView1;


    public String getTextView1() {
        return textView1;
    }

    public void setTextView1(String textView1) {
        this.textView1 = textView1;
    }

    public ModelMakalMatel(String textView1) {
        this.textView1 = textView1;
    }

    public ModelMakalMatel(String textView1, String ivCopy, String ivShare) {
        this.textView1 = textView1;
    }
}
