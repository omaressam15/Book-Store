package com.omaressam.bookstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;

import com.omaressam.bookstore.Model.SingForm;
import com.omaressam.bookstore.Model.Token;
import com.omaressam.bookstore.Model.User;
import com.omaressam.bookstore.Splash.SplashActivity;
import com.omaressam.bookstore.network.api.ApiClient;
import com.omaressam.bookstore.network.service.APIInterface;
import com.omaressam.bookstore.util.PrefManager;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profile_Fragment extends Fragment implements View.OnClickListener {

    private View profile;
    private View sing;

    private Context context;

    private Button singin;
    private Button SingUp;
    private Button sign_button;

    private EditText EnterName;

    private EditText mobile;

    private EditText Email1;

    private EditText Address;

    private EditText password;

    private Group signUpg;

    //Profile
    private ImageView imageProfile;
    private TextView TvNameProfile;
    private TextView TvNPhoneProfile;
    private TextView TvEmailProfile;
    private TextView TvAddress;

    private TextView LogOut;

    public Profile_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        context = Objects.requireNonNull(getActivity()).getApplicationContext();
        initView(view);

        if (PrefManager.retrieveAccessToken(Objects.requireNonNull(getActivity())) != null) {
            profile.setVisibility(View.VISIBLE);
            sing.setVisibility(View.GONE);

            getProfile(PrefManager.retrieveAccessToken(getActivity()));

            initProfileView(view);

        } else {
            profile.setVisibility(View.GONE);
            sing.setVisibility(View.VISIBLE);

            initSighView(view);
        }
        return view;
    }

    private void initSighView(View view) {

        signUpg = view.findViewById(R.id.sign_sign_up_group);

        singin = view.findViewById(R.id.sign_signIn_header_button);
        SingUp = view.findViewById(R.id.sign_signUp_header_button);
        sign_button = view.findViewById(R.id.sign_button);

        EnterName = view.findViewById(R.id.sign_name_editText);

        mobile = view.findViewById(R.id.sign_mobile_editText);

        Email1 = view.findViewById(R.id.sign_email_editText);

        Address = view.findViewById(R.id.sign_address_editText);

        password = view.findViewById(R.id.sign_password_editText);

        singin.setOnClickListener(this);
        SingUp.setOnClickListener(this);
        sign_button.setOnClickListener(this);

    }

    private void initProfileView(View view) {

        imageProfile = view.findViewById(R.id.profileImage_profile_header_imageView);
        TvNameProfile = view.findViewById(R.id.userName_profile_header_textView);
        TvEmailProfile = view.findViewById(R.id.email_profile_header_textView);
        TvNPhoneProfile = view.findViewById(R.id.phone_profile_header_textView);
        LogOut = view.findViewById(R.id.logOut_profile_header_textView);
        LogOut.setOnClickListener(this);


    }

    private void initView(View view) {
        profile = view.findViewById(R.id.profile);
        sing = view.findViewById(R.id.sing);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_signUp_header_button:
                signUpg.setVisibility(View.VISIBLE);

                SingUp.setTextAppearance(context, R.style.ButtonClicked);
                singin.setTextAppearance(context, R.style.ButtonDisable);

                sign_button.setText(R.string.sign_up);
                break;

            case R.id.sign_signIn_header_button:
                signUpg.setVisibility(View.GONE);

                SingUp.setTextAppearance(context, R.style.ButtonDisable);
                singin.setTextAppearance(context, R.style.ButtonClicked);

                sign_button.setText(R.string.sign_in);
                break;

            case R.id.sign_button:
                String email = Email1.getText().toString();
                String password1 = password.getText().toString();


                if (signUpg.getVisibility() == View.VISIBLE) {
                    String name = EnterName.getText().toString();
                    String phone = mobile.getText().toString();
                    String address = Address.getText().toString();
                    signUp(new SingForm(name, phone, email, address, password1));

                } else {
                    signIn(new SingForm(email, password1));

                }
                break;

                case R.id.logOut_profile_header_textView:
                    PrefManager.storeAccessToken(Objects.requireNonNull(getActivity()),null);
                    resetApp();
                    break;
        }


    }
    private void getProfile(String accessToken) {
        APIInterface anInterface = ApiClient.getClient().create(APIInterface.class);
        anInterface.getProfile(accessToken).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();

                    assert user != null;
                    Picasso.get().load(user.getImage()).into(imageProfile);
                    TvNameProfile.setText(user.getName());
                    TvEmailProfile.setText(user.getEmail());
                    TvNPhoneProfile.setText(user.getPhone());

                }
            }

            @Override
            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void signIn(SingForm singForm) {
        APIInterface anInterface = ApiClient.getClient().create(APIInterface.class);
        anInterface.login(singForm).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NotNull Call<Token> call, @NotNull Response<Token> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Welcome back", Toast.LENGTH_SHORT).show();
                    assert response.body() != null;
                    storeToken(response.body());
                    resetApp();

                } else {
                    Toast.makeText(context, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NotNull Call<Token> call, @NotNull Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void signUp(SingForm singForm) {
        APIInterface anInterface = ApiClient.getClient().create(APIInterface.class);
        anInterface.register(singForm).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NotNull Call<Token> call, @NotNull Response<Token> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
                    assert response.body() != null;
                    storeToken(response.body());
                    resetApp();

                }

            }

            @Override
            public void onFailure(@NotNull Call<Token> call, @NotNull Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void storeToken (Token token){
        PrefManager.storeAccessToken(Objects.requireNonNull(getActivity()),token.getAccessToken());
    }

    private void resetApp()  {
        Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(),SplashActivity.class));
        getActivity().finish();
    }
}
