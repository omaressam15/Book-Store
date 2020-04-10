package com.omaressam.bookstore.Model;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omaressam.bookstore.R;
import com.squareup.picasso.Picasso;

class Books_Holder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView namebook;
    private TextView price10;
    private TextView tvoldprice;
    private View viewHasDiscount;
    private RatingBar ratingBar;
    private TextView tvFree;
    private BookType bookType;
    private TextView NameAuthor;
    public Button btnOpen;

    Books_Holder(@NonNull View itemView, BookType bookType) {
        super(itemView);
        this.bookType = bookType;
        initViewFeatured(itemView);
    }

    private void initViewFeatured(View view) {
        imageView = view.findViewById(R.id.book_imageView);
        namebook = view.findViewById(R.id.book_name_textView);
        price10 = view.findViewById(R.id.book_price_textView);
        tvoldprice = view.findViewById(R.id.book_old_price_textView);
        viewHasDiscount = view.findViewById(R.id.group);
        tvFree = view.findViewById(R.id.book_free_textView);
        btnOpen = view.findViewById(R.id.book_open_button);
        NameAuthor = view.findViewById(R.id.book_authorName_textView);

        if (bookType == BookType.Featured || bookType == BookType.My_Books) {

            ratingBar = view.findViewById(R.id.book_ratingBar);
            btnOpen = view.findViewById(R.id.book_open_button);

        }
    }

    void bindView10(Books books) {
        Picasso.get()
                .load(books.getImage())
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);

        namebook.setText(books.getName());

        if (bookType == BookType.My_Books) {
            tvFree.setVisibility(View.GONE);
            viewHasDiscount.setVisibility(View.GONE);
            price10.setVisibility(View.GONE);
            btnOpen.setVisibility(View.VISIBLE);
            namebook.setVisibility(View.VISIBLE);
            ratingBar.setVisibility(View.GONE);

        } else if (bookType == BookType.Featured || bookType == BookType.Home) {

            if (bookType == BookType.Featured) {
                btnOpen.setVisibility(View.GONE);
                ratingBar.setRating(books.getRating());
            }

                if (books.getPrice() == 0) {
                    tvFree.setVisibility(View.VISIBLE);
                    viewHasDiscount.setVisibility(View.GONE);
                    price10.setVisibility(View.GONE);

                } else {
                    tvFree.setVisibility(View.GONE);
                    price10.setVisibility(View.VISIBLE);

                    if (books.getDiscount() != 0) {
                        viewHasDiscount.setVisibility(View.VISIBLE);

                        tvoldprice.setText(String.valueOf(books.getPrice()));
                        double disconut = (books.getPrice() - (books.getPrice() * ((double) books.getDiscount() / 100)));
                        price10.setText(String.valueOf(disconut));

                    } else {
                        viewHasDiscount.setVisibility(View.GONE);
                        price10.setText(String.valueOf(books.getPrice()));

                    }
                }
            }
        }
    }