package com.dd.database.sqlite.makalMatel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dd.database.sqlite.R;
import com.dd.database.sqlite.zhaniltpashtar.DatabaseAccessZhaniltpashtar;

import java.util.List;

public class FragmentMainMakal extends ListFragment {
    List<String> stringList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Setting Title
        getActivity().setTitle(getString(R.string.makal_matel_zhinagi));

        DatabaseAccessZhaniltpashtar databaseAccess = DatabaseAccessZhaniltpashtar.getInstance(getContext());
        databaseAccess.open();
        stringList = databaseAccess.getListMakal();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, stringList);
        setListAdapter(adapter);
        databaseAccess.close();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(v.getContext(), ActivityMakalList.class);
        intent.putExtra("position", position);
        intent.putExtra("itemName", stringList.get(position));
        startActivity(intent);
    }
}
