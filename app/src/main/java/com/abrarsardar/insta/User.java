package com.abrarsardar.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.abrarsardar.appc.R;

public class User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();
    }
}