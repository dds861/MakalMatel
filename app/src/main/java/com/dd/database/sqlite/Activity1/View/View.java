package com.dd.database.sqlite.Activity1.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dd.database.sqlite.Activity2.View.View2;
import com.dd.database.sqlite.Activity1.Presenter.IPresenter;
import com.dd.database.sqlite.Activity1.Presenter.Presenter;
import com.dd.database.sqlite.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class View extends AppCompatActivity implements IView {

    private IPresenter iPresenter;
    private ListView mListview1;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //реклама от google
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        iPresenter = new Presenter(this);
        iPresenter.setDataToListview();

        //создаем Кликер на item-ы в listview
        mListview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long l) {

                //создаем и инициализируем Intent
                Intent intent = new Intent(getApplicationContext(), View2.class);

                //добавляем дополнительный параметр, позицию которуб Кликнули
                intent.putExtra("position", position);

                //запускаем Intent, и открываем новый activity
                startActivity(intent);

            }
        });
    }

    private void initView() {
        mAdView = (AdView) findViewById(R.id.adView);
        mListview1 = (ListView) findViewById(R.id.listview1);
    }

    @Override
    public void setDataToListview(List<String> stringList) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
        mListview1.setAdapter(arrayAdapter);
    }
}
