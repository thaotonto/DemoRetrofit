package com.example.tonto.demoretrofit.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tonto.demoretrofit.MainActivity;
import com.example.tonto.demoretrofit.R;
import com.example.tonto.demoretrofit.networks.register.RegisterRequest;
import com.example.tonto.demoretrofit.networks.register.RegisterResponse;
import com.example.tonto.demoretrofit.networks.register.RegisterService;
import com.example.tonto.demoretrofit.networks.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment {
    private EditText etUsername;
    private EditText etPassword;
    private Button btSignUp;

    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        getReferences(view);
        setupUI();
        return view;
    }


    private void getReferences(View view) {
        etUsername = (EditText) view.findViewById(R.id.et_username);
        etPassword = (EditText) view.findViewById(R.id.et_password);
        btSignUp = (Button) view.findViewById(R.id.bt_sign_up);
    }

    private void setupUI() {
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
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
            RetrofitFactory.getInstance().createService(RegisterService.class)
                    .register(new RegisterRequest(etUsername.getText().toString(), etPassword.getText().toString()))
                    .enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            if (response.code() == 200 || response.code() == 307) {
                                Toast.makeText(getActivity(), "Your account is ready!", Toast.LENGTH_SHORT).show();
                                ((MainActivity) getActivity())
                                        .changeScreen((new SignIn())
                                                .setUser(etUsername.getText().toString(), etPassword.getText().toString()), true);
                            } else if (response.code() == 400) {
                                Toast.makeText(getActivity(), "User already exists!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), "No connection", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
