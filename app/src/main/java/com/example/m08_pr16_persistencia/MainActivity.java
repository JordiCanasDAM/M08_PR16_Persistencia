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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);

        //Recuperacio text de l'arxiu
        try {

            //Apertura inputs
            FileInputStream fileIn = getApplicationContext().openFileInput("dades.txt");
            InputStreamReader dataIn = new InputStreamReader(fileIn);
            BufferedReader textIn = new BufferedReader(dataIn);

            //Lectura fitxer
            String line;
            while ((line = textIn.readLine()) != null){
                //Actualitzacio text
                editText.setText(editText.getText().toString() + line);
            }

            //Tancament inputs
            textIn.close();
            dataIn.close();
            fileIn.close();

        } catch (IOException e) { //Cas error
            Toast.makeText(getApplicationContext(), "ERROR: UNABLE_TO_LOAD_FILE_CONTENTS", Toast.LENGTH_SHORT).show();
            Log.i("ERROR", e.getStackTrace().toString());
        }

        //Actualitzacio text arxiu
        editText.addTextChangedListener(new TextWatcher() {

            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {} //IGNORE
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {} //IGNORE

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

                } catch (IOException e) { //Cas ERROR
                    Toast.makeText(getApplicationContext(), "ERROR: UNABLE_TO_UPDATE_FILE", Toast.LENGTH_SHORT).show();
                    Log.i("ERROR", e.getStackTrace().toString());
                }
            }
        });

        //Mode de boto (sense us en aquesta versio)
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