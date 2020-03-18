package bookstore.Recyclview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omaressam.bookstore.R;

import java.util.List;

public class book_Adabter extends RecyclerView.Adapter<book_Holder> {

    private List <String> books;
    private Book_Item_Click bookItemClick;

    public book_Adabter(Book_Item_Click bookItemClick ,List<String> books) {
        this.bookItemClick = bookItemClick;
        this.books = books;

    }


    @NonNull
    @Override
    public book_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclview, parent, false);

        return new book_Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull book_Holder holder, final int position) {
        holder.bindView(books.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookItemClick.onItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size() ;
    }
}
