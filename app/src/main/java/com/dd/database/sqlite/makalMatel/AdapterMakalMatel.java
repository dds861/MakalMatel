package com.dd.database.sqlite.makalMatel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dd.database.sqlite.R;

import java.util.List;

/**
 * Created by dds86 on 23-Nov-17.
 */

public class AdapterMakalMatel extends ArrayAdapter<ModelMakalMatel> {
    private List<ModelMakalMatel> objects;

    public AdapterMakalMatel(@NonNull Context context, @NonNull List<ModelMakalMatel> objects) {
        super(context, 0, objects);
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.items, parent, false);

        TextView textView1 = view.findViewById(R.id.text1);

        ModelMakalMatel product = getItem(position);

        String s1 = product.getTextView1();


        textView1.setText(s1.replaceAll("\\\\n", "\n"));

        return view;
    }



}
