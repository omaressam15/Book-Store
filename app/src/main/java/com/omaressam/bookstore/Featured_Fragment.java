package com.omaressam.bookstore;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Featured_Fragment extends Fragment implements Featured_item_Click {

    private RecyclerView recyclerView7;
    private NavController navController7;
    private List<Books> booksList;

    public Featured_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_featured, container, false);
        recyclerView7 = view.findViewById(R.id.Recycleview10);
        initView(view);
        loadBooks();
        return view;
    }
    private void initView(View view) {
        recyclerView7 = view.findViewById(R.id.Recycleview10);

        ImageView imgBack = view.findViewById(R.id.back_imageView);
        imgBack.setOnClickListener(v -> navController7.popBackStack());
    }

    private void loadBooks() {
        APIInterface apiService = ApiClient.getClient().create(APIInterface.class);
        apiService.getBooks().enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(@NotNull Call<List<Books>> call, @NotNull Response<List<Books>> response) {
                if (response.isSuccessful()) {
                    booksList = response.body();
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

        recyclerView7.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        Books_Adabter featuredAdabter = new Books_Adabter(booksList, Featured_Fragment.this, BookType.Featured,null);
        recyclerView7.setAdapter(featuredAdabter);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController7 = Navigation.findNavController(view);
    }

    @Override
    public void onItemClicked3(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("BOOK",booksList.get(position));
        navController7.navigate(R.id.action_featured_Fragment_to_bookDetailsFragment,bundle);

    }

}
