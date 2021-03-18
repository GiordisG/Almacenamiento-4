package com.example.almacenamiento4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Button btn_grabar;
    Button btn_recuperar;
    EditText txt_entrada;
    EditText txt_archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_grabar = findViewById(R.id.buttonGrabar);
        btn_recuperar = findViewById(R.id.buttonRecuperar);
        txt_entrada = findViewById(R.id.txtMultinieo);
        txt_archivo = findViewById(R.id.txtArchivo);
        SharedPreferences pf = getSharedPreferences("datos", Context.MODE_PRIVATE);

    }
    public void grabar(View v){
        String nom_archivo = txt_archivo.getText().toString();
        nom_archivo = nom_archivo.replace('/', '-');
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(nom_archivo, Activity.MODE_PRIVATE));
            archivo.write(txt_entrada.getText().toString());
            archivo.flush();
            archivo.close();
        } catch (Exception e){

        }
        Toast t = Toast.makeText(getApplicationContext(), "Datos Ingresados", Toast.LENGTH_LONG);
        t.show();
        txt_archivo.setText("");
        txt_entrada.setText("");
    }
    public void recuperar(View v){
        String nom_archivo = txt_archivo.getText().toString();
        nom_archivo = nom_archivo.replace('/', '-');
        boolean enco = false;
        String[] archivos = fileList();
        for (int i = 0; i < archivos.length; i++){
            enco = true;
            if(enco == true){
                try {
                    InputStreamReader archivo = new InputStreamReader(openFileInput(nom_archivo));
                    BufferedReader br = new BufferedReader(archivo);
                    String linea = br.readLine();
                    String todo = "";
                    while(linea != null){
                        todo = todo + linea + "\n";
                        linea =br.readLine();
                    }
                    br.close();
                    archivo.close();
                    txt_entrada.setText(todo);
                }
                catch (Exception e){

                }

            }
            else {
                Toast.makeText(getApplicationContext(), "No hay datos grabados para dicha fecha", Toast.LENGTH_LONG).show();
                txt_entrada.setText("");
            }
        }
    }
}