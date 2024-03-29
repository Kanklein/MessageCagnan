package com.example.messagecagnan;

import static com.example.messagecagnan.MainActivity.PREF_NIGHT;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MessageActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.messagecagnan.MESSAGE";
    public static final String EXTRA_RESULT = "com.example.messagecagnan.RESULT";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        SharedPreferences preferences = getSharedPreferences("Cagnan", Activity.MODE_PRIVATE);
        boolean night = preferences.getBoolean(PREF_NIGHT, false);
        initializeNightMode(night);
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView tvMessage = findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        btnSaveListenerMethod();

    }

    private void initializeNightMode(boolean night) {
        ConstraintLayout clMessage = findViewById(R.id.clMessage);
        int[] textviews = {R.id.tvLabel,R.id.tvMessage,R.id.rbGood,R.id.rbBad};
        if(night){
            clMessage.setBackgroundColor(Color.BLACK);
            for (int tv : textviews){
                TextView v = findViewById(tv);
                v.setTextColor(Color.WHITE);
            }
        }else{
            clMessage.setBackgroundColor(Color.WHITE);
            for (int tv : textviews){
                TextView v = findViewById(tv);
                v.setTextColor(Color.BLACK);
            }
        }

    }

    private void btnSaveListenerMethod() {
        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(view -> {
            boolean good;
            RadioButton rbGood = findViewById(R.id.rbGood);
            good = rbGood.isChecked();
            Intent outIntent = new Intent();
            outIntent.putExtra(EXTRA_RESULT, good);
            setResult(RESULT_OK,outIntent);
            finish();
        });
    }
}
