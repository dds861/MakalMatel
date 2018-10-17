package com.dd.database.sqlite.Activity2.Presenter;

public interface IPresenter2 {

   void setDataToListview();

//    void setDataToListview(String searchText);

    void setDataToListviewSearch(String searchText);

    String getClickedPosition();
}
