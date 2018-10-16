package com.dd.database.sqlite.Activity2.View;

import java.util.List;

public interface IView2 {

    int getClickedPosition();

    void setDataToAdapter(List<String> categoriesToList);
}
