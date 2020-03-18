package bookstore.Recyclview;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omaressam.bookstore.R;
import com.squareup.picasso.Picasso;

public class book_Holder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public book_Holder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);

    }

     void initView(View view) {
        imageView = view.findViewById(R.id.imageView2);

    }
    void bindView(String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder_error)
                .into(imageView);

    }
}
