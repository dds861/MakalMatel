package com.dd.database.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        int position = getIntent().getIntExtra("position",0);

        listView = (ListView) findViewById(R.id.listview2);
        List<Product> products = databaseAccess.getList(position+1);
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), products);
        listView.setAdapter(myAdapter);
    }
}
