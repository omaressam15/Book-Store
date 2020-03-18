package com.omaressam.bookstore;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.omaressam.bookstore.network.service.APIInterface2;
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

    public Featured_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_featured, container, false);
        recyclerView7 = view.findViewById(R.id.Recycleview10);
        gooks(view);
        return view;
    }

    private void gooks(View view ) {

        APIInterface2 apiInterface2 = ApiClient.getClient().create(APIInterface2.class);

        apiInterface2.getBooks().enqueue(new Callback<List<Featured>>() {
            @Override
            public void onResponse(Call<List<Featured>> call, Response<List<Featured>> response) {
                List<Featured> featuredList = response.body();

                recyclerView7.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));


                Featured_Adabter featuredAdabter = new Featured_Adabter(featuredList, Featured_Fragment.this);
                recyclerView7.setAdapter(featuredAdabter);
            }

            @Override
            public void onFailure(Call<List<Featured>> call, Throwable t) {

            }
        });
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController7 = Navigation.findNavController(view);
    }

    @Override
    public void onItemClicked3(int position) {

    }
}
