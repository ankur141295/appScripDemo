package com.appscrip.triviaapp.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.appscrip.triviaapp.R;
import com.appscrip.triviaapp.database.DatabaseHelper;
import com.appscrip.triviaapp.databinding.ActivityQuestion3Binding;
import com.appscrip.triviaapp.summary.SummaryActivity;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.appscrip.triviaapp.questions.Question2Activity.USERNAME;
import static com.appscrip.triviaapp.summary.SummaryActivity.FLAG_COLORS;

public class Question3Activity extends AppCompatActivity {

    public static final String CRICKETER = "CRICKETER";
    private ActivityQuestion3Binding binding;
    private String username, cricketerName;
    private ArrayList<String> colorList = new ArrayList<>();
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setting up viewBinding*/
        binding = ActivityQuestion3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        setup();
        listener();
    }

    /*init method to initialize global variables*/
    private void init() {
        databaseHelper = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle.getString(USERNAME) != null) {
            username = bundle.getString(USERNAME);
            cricketerName = bundle.getString(CRICKETER);
        }
    }


    /*Setup method to setup toolbar*/
    private void setup() {
        setSupportActionBar(binding.myToolBar.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    /*Listener method for all clickListener*/
    private void listener() {
        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*check whether checkbox is clicked or not i.e if colorList is not empty*/
                if (!colorList.isEmpty()) {
                    String flagColor = TextUtils.join(", ", colorList);   // using TextUtils method to convert ArrayList to String
                    addDataToDatabase(flagColor);   //adding data to database and showing summary screen;
                } else {
                    Snackbar.make(binding.btNext, getText(R.string.checkbox_error), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    /*method to add data to database and change activity*/
    private void addDataToDatabase(String colors) {
        String currentDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        databaseHelper.addTriviaData(username,cricketerName,colors,currentDate,currentTime);

        Intent intent = new Intent(Question3Activity.this, SummaryActivity.class);
        intent.putExtra(USERNAME, username);
        intent.putExtra(CRICKETER,cricketerName);
        intent.putExtra(FLAG_COLORS,colors);
        startActivity(intent);
    }

    /*toolbar option for back clickListener*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    /*This method is used to populate colorList array based on checkbox selection*/
    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.cb_white:
                if (checked)
                    colorList.add("White");
                else
                    colorList.remove("White");
                break;
            case R.id.cb_green:
                if (checked)
                    colorList.add("Green");
                else
                    colorList.remove("Green");
                break;
            case R.id.cb_orange:
                if (checked)
                    colorList.add("Orange");
                else
                    colorList.remove("Orange");
                break;
            case R.id.cb_yellow:
                if (checked)
                    colorList.add("Yellow");
                else
                    colorList.remove("Yellow");
                break;
        }
    }

}