package com.appscrip.triviaapp.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.appscrip.triviaapp.MainActivity;
import com.appscrip.triviaapp.R;
import com.appscrip.triviaapp.databinding.ActivityQuestion1Binding;
import com.google.android.material.snackbar.Snackbar;

import static com.appscrip.triviaapp.questions.Question2Activity.USERNAME;

public class Question1Activity extends AppCompatActivity {

    private ActivityQuestion1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setting up viewBinding*/
        binding = ActivityQuestion1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setup();
        listener();
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

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.tilName.getEditText().getText().toString().trim().length() > 0) {
                    Intent intent = new Intent(Question1Activity.this, Question2Activity.class);
                    intent.putExtra(USERNAME, binding.tilName.getEditText().getText().toString());
                    startActivity(intent);
                } else {
                    Snackbar.make(binding.next, getText(R.string.name_error_msg), Snackbar.LENGTH_LONG).show();
                }
            }
        });

        /*keyboard ime options action*/
        binding.tilName.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.next.performClick();
                    return true;
                }
                return false;
            }
        });

        /*Textwatcher to validate name*/
        binding.tilName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().startsWith(" ")) {
                    binding.tilName.getEditText().setText("");
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.quit))
                    .setMessage((getString(R.string.quit_playing)))

                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            /*to check whether activity stack is empty or not in case of restarting the game from Summary screen */
                            if (isTaskRoot()) {
                                startActivity(new Intent(Question1Activity.this, MainActivity.class));
                                finish();
                            } else {
                                finish();
                            }

                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}