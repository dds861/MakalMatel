package com.dd.database.sqlite.old.Activity2.View;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.dd.database.sqlite.old.Activity2.Presenter.IPresenter2;
import com.dd.database.sqlite.old.Activity2.Presenter.Presenter2;
import com.dd.database.sqlite.old.MakeAnimation;
import com.dd.database.sqlite.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class View2 extends AppCompatActivity implements IView2, View.OnClickListener {


    private AdView mAdView;
    private IPresenter2 iPresenter2;
    private RecyclerView mRecyclerview2;
    private EditText mSearchEt;
    private ImageView mClearSearch2Iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

        //Setting TITLE in Activity
        getSupportActionBar().setTitle(getClickedItemName());

        //реклама от google
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        iPresenter2 = new Presenter2(this);
        iPresenter2.setDataToListview();

        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                iPresenter2.setDataToListviewSearch(editable.toString());
            }
        });
    }

    @Override
    public String getClickedItemName() {
        return getIntent().getStringExtra("selectedFromList");

    }

    private void initView() {
        mAdView = findViewById(R.id.adView);
        mRecyclerview2 = findViewById(R.id.recyclerview2);
        mSearchEt = findViewById(R.id.et_search);
        mClearSearch2Iv = findViewById(R.id.iv_clearSearch2);
        mClearSearch2Iv.setOnClickListener(this);
    }

    @Override
    public void setDataToAdapter(List<String> stringList) {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerview2.setLayoutManager(linearLayoutManager);

        MyAdapter2 myAdapter = new MyAdapter2(this, stringList);
        mRecyclerview2.setAdapter(myAdapter);


//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
//        mListview2.setAdapter(arrayAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_clearSearch2:
                MakeAnimation.makeAnimationOnView(R.id.iv_clearSearch2, Techniques.FadeOut, 150, 0, v);
                MakeAnimation.makeAnimationOnView(R.id.iv_clearSearch2, Techniques.FadeIn, 350, 0, v);
                mSearchEt.setText("");
                break;
            default:
                break;
        }
    }


}
