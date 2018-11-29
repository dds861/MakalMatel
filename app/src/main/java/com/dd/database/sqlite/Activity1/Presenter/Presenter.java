package com.dd.database.sqlite.Activity1.Presenter;


import android.content.Intent;
import android.net.Uri;

import com.dd.database.sqlite.Activity1.Model.IModel;
import com.dd.database.sqlite.Activity1.Model.Model;
import com.dd.database.sqlite.Activity1.View.IView;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements IPresenter {

    private IView iView;
    private IModel iModel;

    public Presenter(IView iView) {
        this.iView = iView;
        iModel = new Model(iView);
    }

    @Override
    public void setDataToListviewSearch() {
        List<String> list = iModel.getListFromDatabase();

        List<String> stringList = new ArrayList<>();

        for (String s : list) {
            if (!s.isEmpty()) {
                stringList.add(s);
            }
        }
        iView.setDataToListview(stringList);
    }


    @Override
    public void setDataToListviewSearch(String searchText) {
        List<String> listFromDatabase = iModel.getListFromDatabase();
        List<String> listFromDatabaseWithoutEmptyCells = new ArrayList<>();
        List<String> searchList = new ArrayList<>();

        for (String s : listFromDatabase) {
            if (!s.isEmpty()) {
                listFromDatabaseWithoutEmptyCells.add(s);
            }
        }
        for (String s : listFromDatabaseWithoutEmptyCells) {
            if (s.toLowerCase().contains(searchText.toLowerCase())) {
                searchList.add(s);
            }
        }
        iView.setDataToListview(searchList);
    }

    @Override
    public Intent OnTelegramChannelView(){
        final String appName = "org.telegram.messenger";
        final boolean isAppInstalled = iView.isTelegramAppAvailable(appName);
        Intent intent;
        if (isAppInstalled) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=maqal7"));
        } else {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.t.me/maqal7"));
        }
        return intent;
    }


}
