package com.omaressam.bookstore.Featured;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.omaressam.bookstore.R;
import java.util.List;


public class Featured_Adabter extends RecyclerView.Adapter<Featured_Holder> {

    private List<Featured>featuredList;
    private Featured_item_Click featuredItemClick;

    public Featured_Adabter(List<Featured> featuredList, Featured_item_Click featuredItemClick){

         this.featuredList = featuredList;
         this.featuredItemClick=featuredItemClick;
    }

    @NonNull
    @Override
    public Featured_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_item_click,parent,false);
        return new Featured_Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Featured_Holder holder, final int position) {
        Featured featured = featuredList.get(position);
        holder.bindView10(featured);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                featuredItemClick.onItemClicked3(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return featuredList.size();
    }
}
