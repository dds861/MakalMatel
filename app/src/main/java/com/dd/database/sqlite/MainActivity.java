package com.dd.database.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        listView = (ListView) findViewById(R.id.listview1);


        List<Product> products = databaseAccess.getList();

        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), products);

        listView.setAdapter(myAdapter);

    }


}
