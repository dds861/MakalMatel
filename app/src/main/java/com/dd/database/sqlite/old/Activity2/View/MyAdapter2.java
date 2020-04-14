package com.dd.database.sqlite.old.Activity2.View;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.dd.database.sqlite.analytics.AnalyticsConstants;
import com.dd.database.sqlite.analytics.AnalyticsManager;
import com.dd.database.sqlite.old.MakeAnimation;
import com.dd.database.sqlite.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dd on 01.05.2017.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {


    private Context context;
    private List<String> productList = new ArrayList<>();
    private String text;

    MyAdapter2(Context context, List<String> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_makal, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        holder.mTextView.setText(productList.get(position));


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextView;
        ImageView imageViewShare;
        ImageView imageViewCopy;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTextView = itemView.findViewById(R.id.text2);
            this.imageViewShare = itemView.findViewById(R.id.ivShare);
            this.imageViewCopy = itemView.findViewById(R.id.ivCopy);

            imageViewShare.setOnClickListener(this);
            imageViewCopy.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivCopy:

                    //Analytics: copy_clicked
                    AnalyticsManager.registerEvent(AnalyticsConstants.TAG_COPY_CLICKED,null);

                    //эффект нажатия на кнопку
                    MakeAnimation.makeAnimationOnView(R.id.ivCopy, Techniques.FadeOut, 150, 0, view);
                    MakeAnimation.makeAnimationOnView(R.id.ivCopy, Techniques.FadeIn, 350, 0, view);

                    // Gets a handle to the clipboard service.
                    ClipboardManager clipboard2 = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

                    // Creates a new text clip to put on the clipboard
                    ClipData clip = ClipData.newPlainText("simple text", mTextView.getText().toString());
                    Log.i("autolog", "Copy text: " + mTextView.getText().toString());

                    // Set the clipboard's primary clip.
                    clipboard2.setPrimaryClip(clip);
                    Toast.makeText(context, R.string.TextCopied, Toast.LENGTH_SHORT).show();

                    break;

                case R.id.ivShare:

                    //Analytics: copy_clicked
                    AnalyticsManager.registerEvent(AnalyticsConstants.TAG_SHARE_CLICKED,null);

                    MakeAnimation.makeAnimationOnView(R.id.ivShare, Techniques.FadeOut, 150, 0, view);
                    MakeAnimation.makeAnimationOnView(R.id.ivShare, Techniques.FadeIn, 350, 0, view);

                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, mTextView.getText().toString());
                    Log.i("autolog", "Share text: " + mTextView.getText().toString());
                    context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share_using)));

                    break;
            }
        }
    }


}
