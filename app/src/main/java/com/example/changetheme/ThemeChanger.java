package com.example.changetheme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThemeChanger extends AppCompatActivity{

    private static final int STANDART = R.style.Theme_Main;
    private static final int ALTERNATIVE = R.style.Theme_Alternative;
    private TextView tW;
    Integer themeCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        setTheme(sharedPref.getInt("THEME", STANDART));

        setContentView(R.layout.activity_settings);

        initView(sharedPref.getString("THEME_NAME", "Стандартная"));
    }

    private void initView (String themeName) {
        tW = findViewById(R.id.textView);
        tW.setText(themeName);

        findViewById(R.id.button_accept).setOnClickListener(buttonAcceprListener);
        findViewById(R.id.button).setOnClickListener(buttonLightListener);
        findViewById(R.id.button2).setOnClickListener(buttonDarkListener);
    }

    public View.OnClickListener buttonLightListener = v-> {
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("THEME", STANDART);
        editor.putString("THEME_NAME", "Стандартная");
        editor.apply();
        recreate();
    };

    public View.OnClickListener buttonDarkListener = v-> {
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("THEME", ALTERNATIVE);
        editor.putString("THEME_NAME", "Альтернативная");
        editor.apply();
        recreate();
    };

    public View.OnClickListener buttonAcceprListener = v -> {
        Intent intent = new Intent();
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);

        if (sharedPref.getInt("THEME", STANDART) == STANDART) {
            intent.putExtra("THEME_CHANGE", STANDART);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            intent.putExtra("THEME_CHANGE", ALTERNATIVE);
            setResult(RESULT_OK, intent);
            finish();
        }
    };
}