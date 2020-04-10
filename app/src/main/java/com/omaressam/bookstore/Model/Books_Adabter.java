package com.omaressam.bookstore.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omaressam.bookstore.Book_Item_Click2;
import com.omaressam.bookstore.R;

import java.util.List;


public class Books_Adabter extends RecyclerView.Adapter<Books_Holder> {

    private List<Books> booksList;
    private Featured_item_Click featuredItemClick;
    private BookType bookType;
    private Book_Item_Click2 onBookButtonClick;

    public Books_Adabter(List<Books> booksList, Featured_item_Click featuredItemClick,BookType bookType,Book_Item_Click2 itemClick2 ){

        this.onBookButtonClick = itemClick2;
         this.booksList = booksList;
         this.featuredItemClick=featuredItemClick;
         this.bookType = bookType;
    }

    @NonNull
    @Override
    public Books_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layout =0;

        switch (bookType){
            case Home:
                layout =R.layout.item_recyclviewtow;
                break;
            case Featured:
            case My_Books:
                layout = R.layout.featured_item_click;
                break;
        }

        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new Books_Holder(v,bookType);
    }

    @Override
    public void onBindViewHolder(@NonNull Books_Holder holder, final int position) {
        Books books = booksList.get(position);
        holder.bindView10(books);
        if (onBookButtonClick != null) {
            holder.btnOpen.setOnClickListener(v -> {
                String url = booksList.get(position).getUrl();
                onBookButtonClick.onBookButtonClick(url);
            });
        }

        holder.itemView.setOnClickListener(v -> featuredItemClick.onItemClicked3(position));
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
