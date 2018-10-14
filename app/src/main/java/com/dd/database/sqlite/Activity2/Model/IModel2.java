package com.dd.database.sqlite.Activity2.Model;

import java.util.List;

public interface IModel2 {

    List<String> getListFromDatabase();

    //метод возвращаем список МакалМател по определенной колонке
    List<String> getListOfMakal(int columnPosition);
}
