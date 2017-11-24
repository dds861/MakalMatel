package com.dd.database.sqlite;

/**
 * Created by dds86 on 23-Nov-17.
 */

public class Product {
    String textView1, textView2, textView3;

    public String getTextView1() {return textView1;}

    public void setTextView1(String textView1) {this.textView1 = textView1;}

    public Product(String textView1) {
        this.textView1 = textView1;
    }
}
