package com.example.tonto.demoretrofit.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tonto.demoretrofit.MainActivity;
import com.example.tonto.demoretrofit.R;
import com.example.tonto.demoretrofit.networks.LoginRequest;
import com.example.tonto.demoretrofit.networks.LoginResponse;
import com.example.tonto.demoretrofit.networks.LoginService;
import com.example.tonto.demoretrofit.networks.RegisterRequest;
import com.example.tonto.demoretrofit.networks.RegisterResponse;
import com.example.tonto.demoretrofit.networks.RegisterService;
import com.example.tonto.demoretrofit.networks.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignIn extends Fragment {
    private EditText etUsername;
    private EditText etPassword;
    private Button btSignIn;
    private Button btSignUp;
    private ImageView cbRemember;
    private String username;
    private String password;

    public SignIn() {
        // Required empty public constructor
    }

    public SignIn setUser(String username, String password) {
        this.username = username;
        this.password = password;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        getReferences(view);
        setupUI();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupUI();
    }

    private void getReferences(View view) {
        etUsername = (EditText) view.findViewById(R.id.et_username);
        etPassword = (EditText) view.findViewById(R.id.et_password);
        btSignIn = (Button) view.findViewById(R.id.bt_sign_in);
        btSignUp = (Button) view.findViewById(R.id.bt_sign_up);
        cbRemember = (ImageView) view.findViewById(R.id.cb_remember);
    }

    private void setupUI() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_account", Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("remember", false)) {
            etPassword.setText("");
            etUsername.setText("");
            cbRemember.setSelected(false);
        } else {
            cbRemember.setSelected(true);
            etUsername.setText(sharedPreferences.getString("username", ""));
            etPassword.setText(sharedPreferences.getString("password", ""));
        }
        if (username != null) {
            etUsername.setText(username);
            etPassword.setText(password);
            cbRemember.setSelected(false);
        }
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeScreen(new SignUp(), true);
            }
        });
    }

    private void register() {
        if (etUsername.getText().toString().trim().equals("") || etPassword.getText().toString().trim().equals("")) {
            if (etUsername.getText().toString().trim().equals("")) {
                etUsername.setError("This field cannot be blank");
            }
            if (etPassword.getText().toString().trim().equals("")) {
                etPassword.setError("This field cannot be blank");
            }
        } else {
            RetrofitFactory.getInstance().createService(LoginService.class)
                    .login(new LoginRequest(etUsername.getText().toString(), etPassword.getText().toString()))
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.code() == 200) {
                                LoginResponse loginResponse = response.body();
                                System.out.println(loginResponse.getAccessToken());
                                Toast.makeText(getActivity(), "You are in", Toast.LENGTH_SHORT).show();
                            } else if (response.code() == 401) {
                                Toast.makeText(getActivity(), "Username or password is wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), "No connection", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public EditText getEtUsername() {
        return etUsername;
    }

    public EditText getEtPassword() {
        return etPassword;
    }
}
