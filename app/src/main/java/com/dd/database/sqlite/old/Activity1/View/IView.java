package com.dd.database.sqlite.old.Activity1.View;

import java.util.List;

public interface IView {

    void setDataToListview(List<String> categoriesToList);

    boolean isTelegramAppAvailable(String appName);
}
