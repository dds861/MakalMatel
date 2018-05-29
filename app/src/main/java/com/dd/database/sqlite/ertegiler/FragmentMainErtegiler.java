package com.dd.database.sqlite.ertegiler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dd.database.sqlite.R;
import com.dd.database.sqlite.SqlAccess.DatabaseAccess;

import java.util.List;

public class FragmentMainErtegiler extends ListFragment {

    List<String> stringList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Setting Title
        getActivity().setTitle(getString(R.string.kazakh_skazki));

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();
        stringList = databaseAccess.getListSkazkiCategory();
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

    //Реклама Admob
//        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


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
