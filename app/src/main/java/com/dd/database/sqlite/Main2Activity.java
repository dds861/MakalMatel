package com.dd.database.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        int position = getIntent().getIntExtra("position", 0);

        listView = (ListView) findViewById(R.id.listview2);
        List<Product> products = databaseAccess.getList(position + 1);
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), products);
        listView.setAdapter(myAdapter);
    }
}
