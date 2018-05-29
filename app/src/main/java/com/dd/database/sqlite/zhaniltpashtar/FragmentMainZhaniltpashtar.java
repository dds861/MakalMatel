package com.dd.database.sqlite.zhaniltpashtar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dd.database.sqlite.R;

import java.util.List;

public class FragmentMainZhaniltpashtar extends ListFragment {

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Setting Title
        getActivity().setTitle(getString(R.string.zhaniltpashtar));

        DatabaseAccessZhaniltpashtar databaseAccess = DatabaseAccessZhaniltpashtar.getInstance(getContext());
        databaseAccess.open();
        List<String> stringList = databaseAccess.getListSkazki();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, stringList);
        setListAdapter(adapter);
        databaseAccess.close();
    }

}
