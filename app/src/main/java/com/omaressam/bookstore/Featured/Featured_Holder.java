package com.omaressam.bookstore.Featured;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omaressam.bookstore.R;

class Featured_Holder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView namebook;
    private TextView writername;
    private RatingBar ratingBar;

    Featured_Holder(@NonNull View itemView) {
        super(itemView);
        initViewFeatured(itemView);
    }
    private void initViewFeatured(View view) {
        imageView = view.findViewById(R.id.phtobook10);
        namebook = view.findViewById(R.id.namebook10);
        writername = view.findViewById(R.id.Writer10);
        ratingBar = view.findViewById(R.id.ratingBar10);

    }

    void bindView10(Featured featured) {
        featured.loadImage(imageView);
        namebook.setText(featured.getName());
        writername.setText(featured.getAuthor());
        ratingBar.setRating(featured.getRating());
    }
}
