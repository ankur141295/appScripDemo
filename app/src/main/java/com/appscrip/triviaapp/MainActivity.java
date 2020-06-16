package com.appscrip.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.appscrip.triviaapp.databinding.ActivityMainBinding;
import com.appscrip.triviaapp.questions.Question1Activity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        listener();
    }

    private void listener() {

        binding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Question1Activity.class));
            }
        });

    }

//    @Override
//    public void onBackPressed() {
//        /*to avoid user to use android os back button to go back to previous page*/
//
////        super.onBackPressed();
//    }
}