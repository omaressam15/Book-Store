package com.omaressam.bookstore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.omaressam.bookstore.Model.BookType;
import com.omaressam.bookstore.Model.Books;
import com.omaressam.bookstore.Model.Books_Adabter;
import com.omaressam.bookstore.Model.Featured_item_Click;
import com.omaressam.bookstore.network.api.ApiClient;
import com.omaressam.bookstore.network.service.APIInterface;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Book_Fragment extends Fragment implements Featured_item_Click, Book_Item_Click2 {

    private RecyclerView recyclerView5;

    private List<Books> books;

    public Book_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book, container, false);
        recyclerView5= view.findViewById(R.id.recyclerview5);
        getMyBooks();
        return view;
    }

    private void getMyBooks() {
        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        apiInterface.getBooks().enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(@NotNull Call<List<Books>> call, @NotNull Response<List<Books>> response) {
                if (response.isSuccessful()) {
                    books = response.body();
                    setupBooks();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Books>> call, @NotNull Throwable t) {

                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupBooks() {
        Books_Adabter booksAdabter = new Books_Adabter(books,this, BookType.My_Books,this);

        assert getActivity() != null;

        recyclerView5.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView5.setAdapter(booksAdabter);
    }



    @Override
    public void onItemClicked3(int position) {

    }

    @Override
    public void onBookButtonClick(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Objects.requireNonNull(getActivity()).startActivity(browserIntent);
    }
}
