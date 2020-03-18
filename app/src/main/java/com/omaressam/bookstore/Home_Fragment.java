package com.omaressam.bookstore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.omaressam.bookstore.Featured.Featured;
import com.omaressam.bookstore.Featured.Featured_Adabter;
import com.omaressam.bookstore.Featured.Featured_item_Click;
import com.omaressam.bookstore.network.api.ApiClient;
import com.omaressam.bookstore.network.service.APIInterface;
import com.omaressam.bookstore.network.service.APIInterface2;

import java.util.List;

import bookstore.Recyclview.Book_Item_Click;
import bookstore.Recyclview.book_Adabter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment implements Book_Item_Click, Book_Item_Click2, Featured_item_Click {

    private RecyclerView recyclerView10;
    private RecyclerView recyclerView2;
    private NavController navController;
    private APIInterface apiInterface;
    private NavController navController2;
    public Home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView10 = (RecyclerView) view.findViewById(R.id.home_recyclerView);
        recyclerView2 = view.findViewById(R.id.recycltow);

        apiInterface = ApiClient.getClient().create(APIInterface.class);
        recyclerView10.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        getSlider(view);
        initUI2(view);
        return view;
    }

    private void getSlider(View view){

        apiInterface.slider().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List <String> books=response.body();

                book_Adabter bookAdabter = new book_Adabter(Home_Fragment.this, books);

                recyclerView10.setAdapter(bookAdabter);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

    }

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,StaggeredGridLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(gridLayoutManager);


    private void initUI2(View view) {
        APIInterface2 apiInterface2 = ApiClient.getClient().create(APIInterface2.class);

        apiInterface2.getBooks().enqueue(new Callback<List<Featured>>() {
            @Override
            public void onResponse(Call<List<Featured>> call, Response<List<Featured>> response) {
                List<Featured> featuredList = response.body();

                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


                Featured_Adabter featuredAdabter = new Featured_Adabter(featuredList, Home_Fragment.this);
                recyclerView2.setAdapter(featuredAdabter);
            }

            @Override
            public void onFailure(Call<List<Featured>> call, Throwable t) {

            }
        });


//        // TODO: StaggeredGridLayoutManager
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
//        recyclerView2.setLayoutManager(staggeredGridLayoutManager);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onItemClicked2(int position) {
        navController.navigate(R.id.action_home_Fragment_to_featured_Fragment);
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText( getActivity(),"hi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked3(int position) {
        navController.navigate(R.id.action_home_Fragment_to_featured_Fragment);

    }
}
