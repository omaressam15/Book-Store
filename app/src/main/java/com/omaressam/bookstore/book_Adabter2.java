package com.omaressam.bookstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class book_Adabter2 extends RecyclerView.Adapter<book_Holder2> {
    public List<book2>book2s;
    private Book_Item_Click2 itemClick2;

    book_Adabter2(List<book2> book2s, Book_Item_Click2 itemClick2){
        this.book2s = book2s;
        this.itemClick2=itemClick2;
    }


    @NonNull
    @Override
    public book_Holder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclviewtow,parent,false);
        return new book_Holder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull book_Holder2 holder, final int position) {
        book2 book2 = book2s.get(position);
        holder.bindView2( book2 );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick2.onItemClicked2(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book2s.size();
    }
}
