package com.dd.database.sqlite.Activity2.Presenter;


import com.dd.database.sqlite.Activity2.Model.IModel2;
import com.dd.database.sqlite.Activity2.Model.Model2;
import com.dd.database.sqlite.Activity2.View.IView2;

import java.util.ArrayList;
import java.util.List;

public class Presenter2 implements IPresenter2 {

    private IView2 iView2;
    private IModel2 iModel2;

    public Presenter2(IView2 iView2) {
        this.iView2 = iView2;
        iModel2 = new Model2(iView2);
    }

    @Override
    public void setDataToListview() {
        List<String> list = iModel2.getListFromDatabase();

        List<String> stringList = new ArrayList<>();

        for (String s : list) {
            if (!s.isEmpty()) {
                stringList.add(s);
            }
        }


        iView2.setDataToListview(stringList);
    }

    @Override
    public int getClickedPosition() {
        return iView2.getClickedPosition();
    }
}
