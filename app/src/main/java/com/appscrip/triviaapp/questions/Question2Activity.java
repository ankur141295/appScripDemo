package com.appscrip.triviaapp.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import com.appscrip.triviaapp.R;
import com.appscrip.triviaapp.databinding.ActivityQuestion2Binding;
import com.google.android.material.snackbar.Snackbar;

import static com.appscrip.triviaapp.questions.Question3Activity.CRICKETER;

public class Question2Activity extends AppCompatActivity {

    public static final String USERNAME = "USERNAME";
    private ActivityQuestion2Binding binding;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setting up viewBinding*/
        binding = ActivityQuestion2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        setup();
        listener();
    }

    /*init method to initialize global variables*/
    private void init() {
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString(USERNAME) != null)
            username = bundle.getString(USERNAME);
    }

    /*Setup method to setup toolbar*/
    private void setup() {
        setSupportActionBar(binding.myToolBar.toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    /*Listener method for all clickListener*/
    private void listener() {
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*To check whether radio_group value is empty or not, if empty show error message*/
                if(binding.radioGroup.getCheckedRadioButtonId() == -1){
                    Snackbar.make(binding.next, getText(R.string.radioButton_error), Snackbar.LENGTH_LONG).show();
                }else{
                    int radioButtonId = binding.radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = binding.radioGroup.findViewById(radioButtonId);
                    String cricketerName = radioButton.getText().toString();
                    Intent intent = new Intent(Question2Activity.this, Question3Activity.class);
                    intent.putExtra(USERNAME, username);
                    intent.putExtra(CRICKETER,cricketerName);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}