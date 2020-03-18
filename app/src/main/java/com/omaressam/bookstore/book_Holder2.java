package com.omaressam.bookstore;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class book_Holder2 extends RecyclerView.ViewHolder {
    private ImageView image;
    private TextView tvnamebook;
    private TextView tvpricebook;

    public book_Holder2(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
    }

    void initView(View view) {
        image = view.findViewById(R.id.photobook);
        tvnamebook = view.findViewById(R.id.namebook);
        tvpricebook = view.findViewById(R.id.price);
    }
        void bindView2(book2 book2){
            image.setImageResource(book2.getImage());
            tvnamebook.setText(book2.getName_book());
            tvpricebook.setText(book2.getPrice_book());
    }
}
