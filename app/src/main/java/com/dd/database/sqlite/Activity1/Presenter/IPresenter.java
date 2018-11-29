package com.dd.database.sqlite.Activity1.Presenter;

import android.content.Intent;

public interface IPresenter {

   void setDataToListviewSearch();

    void setDataToListviewSearch(String searchText);

    Intent OnTelegramChannelView();
}
