package com.omaressam.bookstore.Featured;
import android.widget.ImageView;
import com.google.gson.annotations.SerializedName;
import com.omaressam.bookstore.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class Featured implements Serializable {

    @SerializedName("image")
    private String image;

    @SerializedName("rating")
    private float rating;

    @SerializedName("totalRating")
    private int totalRating;

    @SerializedName("name")
    private String name;

    @SerializedName("author")
    private String author;

    @SerializedName("price")
    private int Price;


    private String getImage() {
        return image;

    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    void loadImage(ImageView imageView) {
        Picasso.get()
                .load(getImage())
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder_error)
                .into(imageView);
    }
}
