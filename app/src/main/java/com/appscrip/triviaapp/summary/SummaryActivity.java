package com.appscrip.triviaapp.summary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.appscrip.triviaapp.R;
import com.appscrip.triviaapp.databinding.ActivityQuestion3Binding;
import com.appscrip.triviaapp.databinding.ActivitySummaryBinding;
import com.appscrip.triviaapp.history.HistoryActivity;
import com.appscrip.triviaapp.questions.Question1Activity;
import com.appscrip.triviaapp.questions.Question2Activity;
import com.appscrip.triviaapp.questions.Question3Activity;

import static com.appscrip.triviaapp.questions.Question2Activity.USERNAME;
import static com.appscrip.triviaapp.questions.Question3Activity.CRICKETER;

public class SummaryActivity extends AppCompatActivity {


    public static final String FLAG_COLORS = "colors";
    private ActivitySummaryBinding binding;
    private String username, cricketerName,flagColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySummaryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        setup();
        listener();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString(USERNAME) != null) {
            username = bundle.getString(USERNAME);
            cricketerName = bundle.getString(CRICKETER);
            flagColor = bundle.getString(FLAG_COLORS);
        }
    }

    private void setup() {
        setSupportActionBar(binding.myToolBar.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.summary));
        }

        String capUsername = username.substring(0, 1).toUpperCase() + username.substring(1);  // convert first letter of string to cap

        /*Setting the text to the textViews*/

        binding.tvName.setText(String.format(getString(R.string.hello_name),capUsername));
        binding.tvCricketerAnswer.setText(String.format(getString(R.string.answer),cricketerName));
        binding.tvFlagAnswer.setText(String.format(getString(R.string.answers),flagColor));
    }

    private void listener() {

        binding.btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SummaryActivity.this, Question1Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);    // to clear the activity stack
                startActivity(intent);
            }
        });

        binding.btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SummaryActivity.this, HistoryActivity.class));
            }
        });
    }
}