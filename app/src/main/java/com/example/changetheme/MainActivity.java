package com.example.changetheme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_THEME = 99;
    private static final int STANDART = R.style.Theme_Main;
    private static final int ALTERNATIVE = R.style.Theme_Alternative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        setTheme(sharedPref.getInt("THEME", STANDART));

        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView () {
        findViewById(R.id.changeButton).setOnClickListener(changeButtonListener);
        findViewById(R.id.textTheme);
    }

    public View.OnClickListener changeButtonListener = v -> {
        Intent intent = new Intent(MainActivity.this, ThemeChanger.class);
        intent.putExtra("THEME", STANDART);
        startActivityForResult(intent, REQUEST_THEME);
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_THEME) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK){
            data.getParcelableExtra("THEME_CHANGE");
            SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("THEME", data.getIntExtra("THEME_CHANGE", STANDART));
            editor.apply();
            recreate();
        }
    }
}