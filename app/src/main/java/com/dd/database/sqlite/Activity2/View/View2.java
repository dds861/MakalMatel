package com.dd.database.sqlite.Activity2.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dd.database.sqlite.Activity2.Presenter.IPresenter2;
import com.dd.database.sqlite.Activity2.Presenter.Presenter2;
import com.dd.database.sqlite.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class View2 extends AppCompatActivity implements IView2 {

    ListView listView;
    private AdView mAdView;
    private ListView mListview2;
    private IPresenter2 iPresenter2;

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

        //получаем из предыдущего activity какую позицию выбрали


        //из базы данных получаем список данных по полученной позиции
//        List<Product> products = DatabaseAccess2.getListOfMakal(position + 1, getApplicationContext());
//
//        саздаем и инициализируем адаптер
//        MyAdapter2 myAdapter = new MyAdapter2(getApplicationContext(), products);

        //присваем адаптер к listview
//        listView.setAdapter(myAdapter);


//
//

    }

    @Override
    public int getClickedPosition() {
        int position = getIntent().getIntExtra("position", 0);
        return position;

    }

    private void initView() {
        mAdView = (AdView) findViewById(R.id.adView);
        mListview2 = (ListView) findViewById(R.id.listview2);
    }

    @Override
    public void setDataToListview(List<String> stringList) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
        mListview2.setAdapter(arrayAdapter);
    }
}
