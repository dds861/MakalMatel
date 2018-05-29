package com.dd.database.sqlite.ertegiler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class FragmentMainErtegiler extends ListFragment {

//    ListView listView;
//    ArrayAdapter arrayAdapter;
//    private AdView mAdView;

    //    private FirebaseAnalytics mFirebaseAnalytics;
    List<String> stringList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();

        stringList = databaseAccess.getListSkazkiCategory();
//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringList);
//        listView.setAdapter(arrayAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//            }
//        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, stringList);
        setListAdapter(adapter);

        databaseAccess.close();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(v.getContext(), ActivityListFairyTales.class);
        intent.putExtra("selectedSkazkiCategoryPosition", position);
        intent.putExtra("selectedSkazkiCategoryName", stringList.get(position));

        startActivity(intent);
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
    //Восстанавливаем тему после лого
//        setTheme(R.style.AppTheme);
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

    //Реклама Admob
//        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

    // Obtain the FirebaseAnalytics instance.
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//        Bundle bundle = new Bundle();
//        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    //Logo and app name on action bar
//        setLogoAndAppName();


//    }

    //Logo and app name on action bar
//    private void setLogoAndAppName() {
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.mipmap.ic_launcher_aldar_kose);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//    }
}
