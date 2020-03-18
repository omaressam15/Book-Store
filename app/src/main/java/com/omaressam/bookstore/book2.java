package com.omaressam.bookstore;

public class book2 {
    private int image;
    private String name_book;
    private String price_book;

    public book2(int image, String name_book, String price_book) {
        this.image = image;
        this.name_book = name_book;
        this.price_book = price_book;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName_book() {
        return name_book;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public String getPrice_book() {
        return price_book;
    }

    public void setPrice_book(String price_book) {
        this.price_book = price_book;
    }
}
