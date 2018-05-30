package com.dd.database.sqlite;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

/**
 * Created by dds86 on 23-Nov-17.
 */

public class MyAdapter2 extends ArrayAdapter<Product> {
    private List<Product> objects;
    private Context context;


    public MyAdapter2(@NonNull Context context, @NonNull List<Product> objects) {
        super(context, 0, objects);
        this.objects = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.items2, parent, false);

        final TextView textView1 = view.findViewById(R.id.text2);

        Product product = getItem(position);

        String s1 = product.getTextView1();

        textView1.setText(s1.replaceAll("\\\\n", "\n"));


        ImageView imageViewCopy = view.findViewById(R.id.ivCopyAll);
        ImageView imageViewShare = view.findViewById(R.id.ivShare);


        imageViewCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, R.string.TextCopied, Toast.LENGTH_SHORT).show();
                //эффект нажатия на кнопку
                makeAnimationOnView(R.id.ivCopyAll, Techniques.FadeOut, 150, 0, view);
                makeAnimationOnView(R.id.ivCopyAll, Techniques.FadeIn, 350, 0, view);

                // Gets a handle to the clipboard service.
                ClipboardManager clipboard2 = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

                // Creates a new text clip to put on the clipboard
                ClipData clip = ClipData.newPlainText("simple text", textView1.getText().toString());

                // Set the clipboard's primary clip.
                clipboard2.setPrimaryClip(clip);
            }
        });

        imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //эффект нажатия на кнопку
                makeAnimationOnView(R.id.ivShare, Techniques.FadeOut, 150, 0, view);
                makeAnimationOnView(R.id.ivShare, Techniques.FadeIn, 350, 0, view);

                String shareBody = textView1.getText().toString();
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share_using)));
            }
        });


        return view;
    }

    void makeAnimationOnView(int resourceId, Techniques techniques, int duration, int repeat, View view) {
        YoYo.with(techniques)
                .duration(duration)
                .repeat(repeat)
                .playOn(view.findViewById(resourceId));

    }

}
