package com.dd.database.sqlite.Presenter;


import com.dd.database.sqlite.Model.IModel;
import com.dd.database.sqlite.Model.Model;
import com.dd.database.sqlite.View.IView;

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
    public void setDataToListview() {
        List<String> list = iModel.getListFromDatabase();

        List<String> stringList = new ArrayList<>();

        for(String s:list){
            if(!s.isEmpty()){
                stringList.add(s);
            }
        }


        iView.setDataToListview(stringList);
    }
}
