package com.dd.database.sqlite;

/**
 * Created by dds86 on 23-Nov-17.
 */

public class Product {
    String textView1, ivCopy, ivShare;

    public String getTextView1() {
        return textView1;
    }

    public void setTextView1(String textView1) {
        this.textView1 = textView1;
    }

    public Product(String textView1) {
        this.textView1 = textView1;
    }

    public Product(String textView1, String ivCopy, String ivShare) {
        this.textView1 = textView1;
        this.ivCopy = ivCopy;
        this.ivShare = ivShare;
    }

    public String getIvCopy() {
        return ivCopy;
    }

    public void setIvCopy(String ivCopy) {
        this.ivCopy = ivCopy;
    }

    public String getIvShare() {
        return ivShare;
    }

    public void setIvShare(String ivShare) {
        this.ivShare = ivShare;
    }
}
