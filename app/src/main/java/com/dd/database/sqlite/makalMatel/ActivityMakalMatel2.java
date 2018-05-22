package com.dd.database.sqlite.makalMatel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.dd.database.sqlite.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class ActivityMakalMatel2 extends AppCompatActivity {

    ListView listView;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makal_list);

        //Go Back button (also see in onSupportNavigateUp())
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //реклама от google
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //получаем доступ к базе
//        DatabaseAccess databaseAccess = DatabaseAccess.getDatabaseAccess(this);

        //открываем соединение
//        databaseAccess.open();

        //инициализируем listview
        listView = findViewById(R.id.listview2);

        //получаем из предыдущего activity какую позицию выбрали
        int position = getIntent().getIntExtra("position", 0);

        //из базы данных получаем список данных по полученной позиции
        List<ModelMakalMatel> products = DatabaseAccess.getListOfMakal(position + 1, getApplicationContext());

        //саздаем и инициализируем адаптер
        AdapterMakalMatel2 myAdapter = new AdapterMakalMatel2(getApplicationContext(), products);

        //присваем адаптер к listview
        listView.setAdapter(myAdapter);


//
//

    }

    //Go Back button (also see in onCreate())
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
