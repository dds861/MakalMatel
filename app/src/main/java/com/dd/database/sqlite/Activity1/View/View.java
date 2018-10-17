package com.dd.database.sqlite.Activity1.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.daimajia.androidanimations.library.Techniques;
import com.dd.database.sqlite.Activity1.Presenter.IPresenter;
import com.dd.database.sqlite.Activity1.Presenter.Presenter;
import com.dd.database.sqlite.Activity2.View.View2;
import com.dd.database.sqlite.MakeAnimation;
import com.dd.database.sqlite.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class View extends AppCompatActivity implements IView, android.view.View.OnClickListener {

    private IPresenter iPresenter;
    private ListView mListview1;
    private AdView mAdView;
    private EditText mSearchEt;
    private ImageView mDeleteSearchTextIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // Show “Logo” in action bar:
        setLogoAndAppName();

        //реклама от google
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        iPresenter = new Presenter(this);
        iPresenter.setDataToListviewSearch();

        Log.i("autolog", "Activity1 loaded");

        //создаем Кликер на item-ы в listview
        mListview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long l) {

                //создаем и инициализируем Intent
                Intent intent = new Intent(getApplicationContext(), View2.class);

                //добавляем дополнительный параметр, позицию которуб Кликнули
                String selectedFromList = String.valueOf(mListview1.getItemAtPosition(position));
                intent.putExtra("selectedFromList", selectedFromList);

                //запускаем Intent, и открываем новый activity
                startActivity(intent);

            }
        });

        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                iPresenter.setDataToListviewSearch(editable.toString());


            }
        });

    }

    private void initView() {
        mAdView = (AdView) findViewById(R.id.adView);
        mListview1 = (ListView) findViewById(R.id.listview1);
        mSearchEt = (EditText) findViewById(R.id.et_search);
        mDeleteSearchTextIv = (ImageView) findViewById(R.id.iv_clearSearch);
        mDeleteSearchTextIv.setOnClickListener(this);
    }

    @Override
    public void setDataToListview(List<String> stringList) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
        mListview1.setAdapter(arrayAdapter);
    }


    @Override
    public void onClick(android.view.View view) {
        switch (view.getId()) {
            case R.id.iv_clearSearch:
                // TODO 18/10/17
                MakeAnimation.makeAnimationOnView(R.id.iv_clearSearch, Techniques.FadeOut, 150, 0, view);
                MakeAnimation.makeAnimationOnView(R.id.iv_clearSearch, Techniques.FadeIn, 350, 0, view);
                mSearchEt.setText("");

                break;
            default:
                break;
        }
    }

    //Logo and app name on action bar
    private void setLogoAndAppName() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_pen);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
}
