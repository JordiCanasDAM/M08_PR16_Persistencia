package com.example.m08_pr16_persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override public void afterTextChanged(Editable editable) {
                try {

                    //Apertura arxiu
                    FileOutputStream fileOut = getApplicationContext().openFileOutput("dades.txt", Context.MODE_PRIVATE);
                    OutputStreamWriter textOut = new OutputStreamWriter(fileOut);

                    //Operacions arxiu
                    textOut.write(editText.getText().toString());

                    //Tancament arxiu
                    textOut.close();
                    fileOut.close();

                } catch (Exception e) { //Cas ERROR
                    Toast.makeText(getApplicationContext(), "ERROR: UNABLE_TO_USE_FILE ", Toast.LENGTH_SHORT).show();
                    Log.i("ERROR", e.getStackTrace().toString());
                }
            }
        });

        Button button = findViewById(R.id.button);
        button.setVisibility(Button.INVISIBLE);
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                try {

                    //Apertura arxiu
                    FileOutputStream fileOut = getApplicationContext().openFileOutput("dades.txt", Context.MODE_PRIVATE);
                    OutputStreamWriter textOut = new OutputStreamWriter(fileOut);

                    //Operacions arxiu
                    textOut.write("Hello world! This is an internal storage file");

                    //Tancament arxiu
                    textOut.close();
                    fileOut.close();

                } catch (Exception e){ //Cas ERROR
                    Toast.makeText(getApplicationContext(), "ERROR: UNABLE_TO_USE_FILE ", Toast.LENGTH_SHORT).show();
                    Log.i("ERROR", e.getStackTrace().toString());
                }
            }
        });
        */
    }// M onCreate

}// C MainActivity