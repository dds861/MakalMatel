package com.dd.database.sqlite.Widget.Presenter;


import com.dd.database.sqlite.DatabaseOpenHelper;
import com.dd.database.sqlite.Widget.Model.IModelWidget;
import com.dd.database.sqlite.Widget.Model.ModelWidget;
import com.dd.database.sqlite.Widget.View.IViewWidget;

public class PresenterWidget implements IPresenterWidget {

    private IViewWidget iViewWidget;
    private IModelWidget iModelWidget;

    public PresenterWidget(IViewWidget iViewWidget,DatabaseOpenHelper openHelper) {
        this.iViewWidget = iViewWidget;
        this.iModelWidget = new ModelWidget(openHelper);
    }


    @Override
    public String getRandomMakalFromDatabase() {

        String makal = iModelWidget.getRandomMakalFromDatabase();



//        List<String> listFromDatabase = iModelWidget.getListFromDatabase();
//        List<String> listFromDatabaseWithoutEmptyCells = new ArrayList<>();
//        List<String> searchList = new ArrayList<>();
//
//        for (String s : listFromDatabase) {
//            if (!s.isEmpty()) {
//                listFromDatabaseWithoutEmptyCells.add(s);
//            }
//        }
//        for (String s : listFromDatabaseWithoutEmptyCells) {
//            if (s.toLowerCase().contains(searchText.toLowerCase())) {
//                searchList.add(s);
//            }
//        }
//        iViewWidget.setDataToListview(searchList);

        return makal.replaceAll("\\\\n", "\n");
    }


}
