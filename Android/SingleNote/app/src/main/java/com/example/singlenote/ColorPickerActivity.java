package com.example.singlenote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ColorPickerActivity extends AppCompatActivity {

    public static final String EXTRA_CURRENT_COLOR = "EXTRA_CURRENT_COLOR";
    private static final String TAG = ColorPickerActivity.class.getSimpleName();
    private EditText etColor;
    public static final String EXTRA_NEW_COlOR = "EXTRA_NEW_COLOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        etColor = findViewById(R.id.etColor);
        Intent startIntent = getIntent();
        if (startIntent.hasExtra(EXTRA_CURRENT_COLOR)) {
            int currentColor = startIntent.getIntExtra(EXTRA_CURRENT_COLOR, 0);
            String currColor = String.format("#%06X", 0xffffff&currentColor);
            etColor.setText(currColor);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called with: outState = [" + outState + "]");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState() called with: savedInstanceState = [" + savedInstanceState + "]");
    }

    public void onCancel(View view) {
        Log.d(TAG, "cancel() called with: view = [" + view + "]");
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public void onSave(View view) {
        String newColor = etColor.getText().toString();
        Log.d(TAG, "save() called with: view = [" + newColor + "]");
        Intent respIntent = new Intent();
        respIntent.putExtra(EXTRA_NEW_COlOR, newColor);
        setResult(Activity.RESULT_OK, respIntent);
        finish();
    }
}