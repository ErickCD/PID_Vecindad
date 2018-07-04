package com.clair.ecda.pid_vecindad;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SeleccionPersonaje extends AppCompatActivity {

    Context context;
    Intent intentoPersonaje;
    ListView LVNombres;
    ArrayList<String> ALNombres;
    String personaje;
    Button btnPlayAudioSelection;
    MediaPlayer mediaPlayer;
    //ImageView imageVecindad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_personaje);
        context = SeleccionPersonaje.this;
        intentoPersonaje = new Intent(SeleccionPersonaje.this, Personaje.class);


        LVNombres = (ListView)findViewById(R.id.listv_main_personaje);
        btnPlayAudioSelection = (Button) findViewById(R.id.btn_play_audio_selection);
        mediaPlayer = MediaPlayer.create(context, R.raw.vencidad);
        //imageVecindad = (ImageView) findViewById(R.id.img_main_vecindad);


        ALNombres = new ArrayList<>();
        ALNombres.add("El chavo");
        ALNombres.add("La chilindrina");
        ALNombres.add("Don Ramón");
        ALNombres.add("Quico");
        ALNombres.add("Doña Florinda");
        ALNombres.add("Profesor Jirafales");

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, ALNombres);
        LVNombres.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LVNombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaje = ALNombres.get(position);
                Toast.makeText(context, "Has seleccionado a: " + personaje, Toast.LENGTH_SHORT).show();
                intentoPersonaje.putExtra("personaje", personaje);
                mediaPlayer.pause();
                startActivity(intentoPersonaje);
            }
        });

        btnPlayAudioSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlayAudioSelection.setText(getResources().getString(R.string.btnPlay));
                } else {
                    mediaPlayer.start();
                    btnPlayAudioSelection.setText(getResources().getString(R.string.btnPause));
                }
            }
        });

        /*
        imageVecindad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlayAudioSelection.setText(getResources().getString(R.string.btnPlay));
                } else {
                    mediaPlayer.start();
                    btnPlayAudioSelection.setText(getResources().getString(R.string.btnPause));
                }
            }
        });
        */
    }
}
