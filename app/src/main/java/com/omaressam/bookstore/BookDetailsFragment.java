package com.omaressam.bookstore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.omaressam.bookstore.Model.Books;
import com.omaressam.bookstore.network.api.ApiClient;
import com.omaressam.bookstore.network.service.APIInterface;
import com.omaressam.bookstore.util.PrefManager;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsFragment extends Fragment implements View.OnClickListener {

    private ImageView imageView;

    private TextView tvBookName;

    private TextView tvPrice;

    private TextView tvAuthor;

    private RatingBar ratingBar;

    private TextView tvBookInfo;

    private TextView tvAuthorInfo;

    private Button btnPlaceOrder;

    private NavController navController;
    private Books books;

    public BookDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_details, container, false);
        assert getArguments() != null;
        books = (Books) getArguments().getSerializable("BOOK");
        initView(view);
        setUpData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void initView(View view) {

        imageView = view.findViewById(R.id.book_imageView);
        tvBookName = view.findViewById(R.id.book_name_textView);
        tvPrice = view.findViewById(R.id.book_price_textView);
        tvAuthor = view.findViewById(R.id.book_authorName_textView);
        ratingBar = view.findViewById(R.id.book_ratingBar);
        tvBookInfo = view.findViewById(R.id.book_info_textView);
        tvAuthorInfo= view.findViewById(R.id.book_author_info_textView);
        btnPlaceOrder = view.findViewById(R.id.book_place_order_button);

        btnPlaceOrder.setOnClickListener(this);

        ImageView imageView = view.findViewById(R.id.back_imageView);
        imageView.setOnClickListener(v -> navController.popBackStack());
    }

    private void setUpData() {

        Picasso.get()
                .load(books.getImage())
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);

        tvBookName.setText(books.getName());
        tvAuthor.setText(books.getAuthor());
        tvPrice.setText(String.valueOf(books.getPrice()));
        ratingBar.setRating(books.getRating());
        tvBookInfo.setText(books.getBookDescription());
        tvAuthorInfo.setText(books.getAboutAuthor());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.book_place_order_button:
                if (PrefManager.retrieveAccessToken(Objects.requireNonNull(getActivity()))!=null){

                    BuyBook(books.getId());

                }else {
                    Toast.makeText(getActivity(), "You Should login first", Toast.LENGTH_SHORT).show();
                }
        }

    }

    private void BuyBook(String id) {
        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);

        apiInterface.buyBook(PrefManager.retrieveAccessToken(Objects.requireNonNull(getActivity())), id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Order place successfully", Toast.LENGTH_SHORT).show();
                    navController.popBackStack();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}
