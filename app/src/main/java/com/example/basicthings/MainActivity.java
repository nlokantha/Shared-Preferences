package com.example.basicthings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private Button btn_save,btn_apply;
    private Switch switch1;

    public static final String SHARED_PREFS="sharedPrefs";
    public static final String TEXT="text";
    public static final String SWITCH1="switch1";

    private String text;
    private Boolean switchOnOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);
        btn_apply=findViewById(R.id.btn_apply);
        btn_save=findViewById(R.id.btn_save);
        switch1=findViewById(R.id.swith1);

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText().toString());
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        loadData();
        updateView();


    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(TEXT,textView.getText().toString());
        editor.putBoolean(SWITCH1,switch1.isChecked());

        editor.apply();
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT,"");
        switchOnOff=sharedPreferences.getBoolean(SWITCH1,false);

    }

    public void updateView(){
        textView.setText(text);
        switch1.setChecked(switchOnOff);
    }
}