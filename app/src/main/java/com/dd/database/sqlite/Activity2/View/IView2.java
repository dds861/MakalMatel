package com.dd.database.sqlite.Activity2.View;

import java.util.List;

public interface IView2 {

    String getClickedItemName();

    void setDataToAdapter(List<String> categoriesToList);
}
