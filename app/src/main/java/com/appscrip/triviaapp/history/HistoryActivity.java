package com.appscrip.triviaapp.history;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.appscrip.triviaapp.R;
import com.appscrip.triviaapp.database.DatabaseHelper;
import com.appscrip.triviaapp.databinding.ActivityHistoryBinding;
import com.appscrip.triviaapp.databinding.ActivitySummaryBinding;
import com.appscrip.triviaapp.history.model_class.History;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ActivityHistoryBinding binding;
    private DatabaseHelper databaseHelper;
    private ArrayList<History> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setting up viewBinding*/
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        populateList();
        setup();
    }


    /*populate the model_class List by reading the values from database via help of each cursor*/
    private void populateList() {
        Cursor cursor = databaseHelper.getTriviaData();
        if (cursor.getCount() == 0) {
//            if no data is found show a error_image
            binding.rvHistory.setVisibility(View.GONE);
            binding.ivNoData.setVisibility(View.VISIBLE);
        } else {
//            if data is found hide the error_image
            binding.rvHistory.setVisibility(View.VISIBLE);
            binding.ivNoData.setVisibility(View.GONE);

//            iterating through the cursor and populating the historyList
            if (cursor.moveToFirst()) {
                historyList = new ArrayList<>();
                History history;
                do {
                    history = new History();
                    history.setId(cursor.getString(0));
                    history.setName(cursor.getString(1));
                    history.setCricketer(cursor.getString(2));
                    history.setFlagColor(cursor.getString(3));
                    history.setDate(cursor.getString(4));
                    history.setTime(cursor.getString(5));
                    historyList.add(history);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
    }


    /*init method to initialize global variables*/
    private void init() {
        databaseHelper = new DatabaseHelper(this);
        historyList = new ArrayList<>();
    }


    /*Setup method to setup toolbar and recyclerview adapter*/
    private void setup() {
        setSupportActionBar(binding.myToolBar.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.history));
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvHistory.setLayoutManager(layoutManager);
        binding.rvHistory.setAdapter(new HistoryAdapter(historyList));
    }


    /*toolbar option for back clickListener*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}