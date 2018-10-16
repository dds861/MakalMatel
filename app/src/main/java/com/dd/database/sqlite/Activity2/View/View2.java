package com.dd.database.sqlite.Activity2.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dd.database.sqlite.Activity2.Presenter.IPresenter2;
import com.dd.database.sqlite.Activity2.Presenter.Presenter2;
import com.dd.database.sqlite.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class View2 extends AppCompatActivity implements IView2 {


    private AdView mAdView;
    private IPresenter2 iPresenter2;
    private RecyclerView mRecyclerview2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

        //реклама от google
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        iPresenter2 = new Presenter2(this);
        iPresenter2.setDataToListview();


    }

    @Override
    public int getClickedPosition() {
        int position = getIntent().getIntExtra("position", 0);
        return position;

    }

    private void initView() {
        mAdView = (AdView) findViewById(R.id.adView);
        mRecyclerview2 = (RecyclerView) findViewById(R.id.recyclerview2);
    }

    @Override
    public void setDataToAdapter(List<String> stringList) {






        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerview2.setLayoutManager(linearLayoutManager);

        MyAdapter2 myAdapter = new MyAdapter2(this,stringList);
        mRecyclerview2.setAdapter(myAdapter);



//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
//        mListview2.setAdapter(arrayAdapter);
    }


}
