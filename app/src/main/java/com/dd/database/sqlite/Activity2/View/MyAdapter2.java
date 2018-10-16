package com.dd.database.sqlite.Activity2.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dd.database.sqlite.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dd on 01.05.2017.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {


    Context context;
    List<Product> productList = new ArrayList<>();

    public MyAdapter2(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items2, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product  = productList.get(position);

        holder.mTextView.setText(product.getText1());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mTextView = (TextView) itemView.findViewById(R.id.text2);


        }


    }
}
