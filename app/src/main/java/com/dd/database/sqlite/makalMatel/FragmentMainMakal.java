package com.dd.database.sqlite.makalMatel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dd.database.sqlite.R;
import com.dd.database.sqlite.SqlAccess.DatabaseAccess;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class FragmentMainMakal extends Fragment {

    ListView listView;
    private AdView mAdView;
    AdapterMakalMatel myAdapter;


    public interface onSomeEventListener {
        public void someEvent(String s);
    }

    onSomeEventListener someEventListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            someEventListener = (onSomeEventListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_makal_category, null);

        //реклама
//        mAdView = view.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        //получаем доступ к базе
//        DatabaseAccess databaseAccess = DatabaseAccess.getDatabaseAccess(this);

        //открываем соединение
//        databaseAccess.open();

        //инициализируем listview
        listView = view.findViewById(R.id.listview1);

        //из базы данных получаем список данных
        List<ModelMakalMatel> products = DatabaseAccess.getColumnListFromTableSql(0, view.getContext(),getString(R.string.tableMakal));


        //саздаем и инициализируем адаптер
        myAdapter = new AdapterMakalMatel(view.getContext(), products);


        //присваем адаптер к listview
        listView.setAdapter(myAdapter);


        //создаем Кликер на item-ы в listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

//                //создаем и инициализируем Intent
                Intent intent = new Intent(view.getContext(), ActivityMakalList.class);
//
//                //добавляем дополнительный параметр, позицию которуб Кликнули
                intent.putExtra("position", position);
//
//                //запускаем Intent, и открываем новый activity
                startActivity(intent);

                someEventListener.someEvent(String.valueOf(position));


            }
        });

        return view;
    }


}
