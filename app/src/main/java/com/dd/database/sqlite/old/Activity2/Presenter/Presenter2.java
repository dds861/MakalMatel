package com.dd.database.sqlite.old.Activity2.Presenter;


import com.dd.database.sqlite.old.Activity2.Model.IModel2;
import com.dd.database.sqlite.old.Activity2.Model.Model2;
import com.dd.database.sqlite.old.Activity2.View.IView2;

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

        String clickedTextName = iView2.getClickedItemName();
        List<String> list = iModel2.getListFromDatabase();
        int position = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(clickedTextName)) {
                position = i;
                break;
            }
        }

        List<String> list2 = iModel2.getListOfMakal(position);
        List<String> stringList = new ArrayList<>();

        for (String s : list2) {
            if (!s.isEmpty()) {
                stringList.add(s.replaceAll("\\\\n", "\n"));
            }
        }
        iView2.setDataToAdapter(stringList);
    }



    @Override
    public void setDataToListviewSearch(String searchText) {

        String clickedTextName = iView2.getClickedItemName();
        List<String> list = iModel2.getListFromDatabase();
        int position = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(clickedTextName)) {
                position = i;
                break;
            }
        }

        List<String> listFromDatabase = iModel2.getListOfMakal(position);
        List<String> listFromDatabaseWithoutEmptyCells = new ArrayList<>();
        List<String> searchList = new ArrayList<>();

        for (String s : listFromDatabase) {
            if (!s.isEmpty()) {
                listFromDatabaseWithoutEmptyCells.add(s);
            }
        }
        for (String s : listFromDatabaseWithoutEmptyCells) {
            if (s.toLowerCase().contains(searchText.toLowerCase())) {
                searchList.add(s.replaceAll("\\\\n", "\n"));
            }
        }


        iView2.setDataToAdapter(searchList);
    }

    @Override
    public String getClickedPosition() {
        return iView2.getClickedItemName();
    }

}
