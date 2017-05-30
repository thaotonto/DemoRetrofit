package com.example.tonto.demoretrofit;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tonto.demoretrofit.fragment.SignIn;

public class MainActivity extends AppCompatActivity {
    private SignIn signIn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayStartScreen();
        sharedPreferences = getSharedPreferences("my_account", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void displayStartScreen() {
        // 1: Create fragment
        signIn = new SignIn();

        changeScreen(signIn, false);
    }

    public void changeScreen(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(null);

        // commit
        fragmentTransaction.commit();
    }

    public void savePreference(View view) {
        if (view.isSelected()) {
            editor.putBoolean("remember", false);
            editor.commit();
            view.setSelected(false);
        } else {
            view.setSelected(true);
            editor.putBoolean("remember", true);
            editor.putString("username", signIn.getEtUsername().getText().toString());
            editor.putString("password", signIn.getEtPassword().getText().toString());
            editor.commit();
        }
    }
}
