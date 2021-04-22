package com.example.changetheme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;
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
    }

    public View.OnClickListener changeButtonListener = v -> {
        Intent intent= new Intent(MainActivity.this, ThemeChanger.class);
        intent.putExtra("THEME", STANDART);
        startActivity(intent);
    };

}