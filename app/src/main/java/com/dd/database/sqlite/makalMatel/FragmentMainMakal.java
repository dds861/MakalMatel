package com.dd.database.sqlite.makalMatel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import com.dd.database.sqlite.zhaniltpashtar.DatabaseAccessZhaniltpashtar;

import java.util.ArrayList;
import java.util.List;

public class FragmentMainMakal extends ListFragment {

    public interface onSomeEventListener {
        public void someEvent(String s);
    }

    onSomeEventListener someEventListener;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatabaseAccessZhaniltpashtar databaseAccess = DatabaseAccessZhaniltpashtar.getInstance(getContext());
        databaseAccess.open();

        List<String> stringList = new ArrayList<>();

        stringList = databaseAccess.getListMakal();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, stringList);
        setListAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            someEventListener = (onSomeEventListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

}
