package com.dd.database.sqlite.makalMatel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.dd.database.sqlite.R;
import com.dd.database.sqlite.SqlAccess.DatabaseAccess;

import java.util.List;

public class ActivityMakalList extends AppCompatActivity {

    ListView listView;
//    private AdView mAdView;


    //Override method
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makal_list);

        //Go Back button in onCreate() method
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //инициализируем listview
        listView = findViewById(R.id.listview2);

        //получаем из предыдущего activity какую позицию выбрали
        int position = getIntent().getIntExtra("position", 0);

        //Setting Title to ActionBar
        String itemName = getIntent().getStringExtra("itemName");
        getSupportActionBar().setTitle(itemName);

        //из базы данных получаем список данных по полученной позиции
        List<ModelMakalMatel> products = DatabaseAccess.getColumnListFromTableSql(position + 1, getApplicationContext(), getString(R.string.tableMakal));

        //саздаем и инициализируем адаптер
        AdapterMakalMatel2 myAdapter = new AdapterMakalMatel2(getApplicationContext(), products);

        //присваем адаптер к listview
        listView.setAdapter(myAdapter);
    }


}
