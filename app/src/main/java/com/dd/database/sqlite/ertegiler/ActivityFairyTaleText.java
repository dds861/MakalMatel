package com.dd.database.sqlite.ertegiler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dd.database.sqlite.R;

import java.util.List;

public class ActivityFairyTaleText extends AppCompatActivity {


    ListView listView;
    ArrayAdapter arrayAdapter;

    //Реклама Admob
//    private AdView mAdView;



    //Override method
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Восстанавливаем тему после лого
//        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ertegiler_activity_main3);

        //Go Back button in onCreate() method
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Реклама Admob
//        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);




        //Подключаемся к базе
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        //получаем какую сказку выбрали в кирриллице чтоб потом в action bar сделать setTitle
        String selectedSkazkaName = getIntent().getStringExtra("SelectedSkazkaName");

        //получаем позицию выбранной сказку
        int selectedSkazka = getIntent().getIntExtra("SelectedSkazkaPosition", 0);

        //получаем какую Категорию сказок выбрали
        int selectedSkazkiCategoryPosition = getIntent().getIntExtra("SelectedSkazkiCategoryPosition", 0);

        //Logo and app name on action bar
//        setLogoAndAppName(selectedSkazkaName);

        //получаем название таблицы из которой будем брать сказку
        String nameOfSelectedCategoryInLatin = getIntent().getStringExtra("nameOfSelectedCategoryInLatin");


        List<String> names_of_skazki_latin_latin = databaseAccess.getNames("names_of_skazki_latin_latin");

        List<String> names_of_skazki_latin = databaseAccess.getNames(names_of_skazki_latin_latin.get(selectedSkazkiCategoryPosition));

        String ertegi = names_of_skazki_latin.get(selectedSkazka);
        //получаем список сказок на латинице
        List<String> stringListSkazki = databaseAccess.getTable(ertegi, nameOfSelectedCategoryInLatin);


        listView = (ListView) findViewById(R.id.listview3);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringListSkazki);
        listView.setAdapter(arrayAdapter);

    }

    //Logo and app name on action bar
//    private void setLogoAndAppName(String selectedSkazkaName) {
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
////        getSupportActionBar().setLogo(R.mipmap.ic_launcher_aldar_kose);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setTitle(selectedSkazkaName);
//    }


}
