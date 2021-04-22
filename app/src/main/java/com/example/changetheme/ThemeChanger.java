package com.example.changetheme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThemeChanger extends AppCompatActivity{

    private static final int STANDART = R.style.Theme_Main;
    private static final int ALTERNATIVE = R.style.Theme_Alternative;
    private TextView tW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        setTheme(sharedPref.getInt("THEME", STANDART));

        setContentView(R.layout.activity_settings);

        tW = findViewById(R.id.textView);
        tW.setText(sharedPref.getString("THEME_NAME", ""));

        findViewById(R.id.button_accept).setOnClickListener(buttonAcceprListener);
        findViewById(R.id.button).setOnClickListener(buttonLightListener);
        findViewById(R.id.button2).setOnClickListener(buttonDarkListener);
    }

    public View.OnClickListener buttonLightListener = v-> {
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("THEME", STANDART);
        editor.putString("THEME_NAME", "Стандартная");
        editor.commit();
        recreate();
    };

    public View.OnClickListener buttonDarkListener = v-> {
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("THEME", ALTERNATIVE);
        editor.putString("THEME_NAME", "Альтернативная");
        editor.commit();
        recreate();
    };

    public View.OnClickListener buttonAcceprListener = v -> {
        Intent intent= new Intent(ThemeChanger.this, MainActivity.class);
        intent.putExtra("THEME", ALTERNATIVE);
        startActivity(intent);
    };

}