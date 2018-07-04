package com.clair.ecda.pid_vecindad;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Personaje extends AppCompatActivity {

    private Handler handler = new Handler();
    TextView tvNombrePersonaje, tvdescripsion;
    ImageView imgPersonaje;
    Button btnReproducirAudio, btnSalir;
    Intent intentoPersonaje, intentSelectCharacter;
    String nombrePersonaje;
    int imageCharacter2, imageCharacter;
    boolean band = true;
    MediaPlayer mediaPlayer;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);
        context = Personaje.this;

        intentoPersonaje = getIntent();
        Bundle extras = intentoPersonaje.getExtras();
        nombrePersonaje = extras.getString("personaje");
        intentSelectCharacter = new Intent(Personaje.this, SeleccionPersonaje.class);

        //Textos
        tvNombrePersonaje = (TextView) findViewById(R.id.txtv_personaje_nombre);
        tvdescripsion = (TextView) findViewById(R.id.txtv_personaje_info);
        //Imagen
        imgPersonaje = (ImageView) findViewById(R.id.img_personaje_principal);
        //Botones
        btnReproducirAudio = (Button) findViewById(R.id.btn_personaje_play);
        btnSalir = (Button) findViewById(R.id.btn_personaje_regresar);

    }

    @Override
    protected void onResume() {
        super.onResume();

        switch (nombrePersonaje) {
            case "El chavo":
                changeData(nombrePersonaje, getResources().getString(R.string.chavo), R.mipmap.chavoimg1, R.mipmap.chavoimg2);
                mediaPlayer = MediaPlayer.create(context, R.raw.chavo);
                break;
            case "La chilindrina":
                changeData(nombrePersonaje, getResources().getString(R.string.chilindrina), R.mipmap.chilindrinaimg, R.mipmap.chilindrinaimg2);
                mediaPlayer = MediaPlayer.create(context, R.raw.chili);
                break;
            case "Don Ramón":
                changeData(nombrePersonaje, getResources().getString(R.string.ramon), R.mipmap.donramonimg, R.mipmap.donramonimg2);
                mediaPlayer = MediaPlayer.create(context, R.raw.donramon);
                break;
            case "Quico":
                changeData(nombrePersonaje, getResources().getString(R.string.quico), R.mipmap.quicoimg, R.mipmap.quicoimg2);
                mediaPlayer = MediaPlayer.create(context, R.raw.quico);
                break;
            case "Doña Florinda":
                changeData(nombrePersonaje, getResources().getString(R.string.florinda), R.mipmap.florindaimg, R.mipmap.florindaimg2);
                mediaPlayer = MediaPlayer.create(context, R.raw.florinda);
                break;
            case "Profesor Jirafales":
                changeData(nombrePersonaje, getResources().getString(R.string.profesor), R.mipmap.jirafalesimg, R.mipmap.jirafalesimg2);
                mediaPlayer = MediaPlayer.create(context, R.raw.jirafales);
                break;
        }

        btnReproducirAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnReproducirAudio.setText(getResources().getString(R.string.btnPlay));
                } else {
                    mediaPlayer.start();
                    btnReproducirAudio.setText(getResources().getString(R.string.btnPause));
                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                startActivity(intentSelectCharacter);
            }
        });
    }

    private void changeData(String nameCharacter, String description, final int image, final int image2) {
        //image of character
        imageCharacter2 = image2;
        imageCharacter = image;
        //Texts of characters
        tvNombrePersonaje.setText(nameCharacter);
        tvdescripsion.setText(description);
        //send image to ImageView
        imgPersonaje.setImageResource(image);
        //Start the timer loop
        handler.postDelayed(runnable, 3000);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (band) {
                imgPersonaje.setImageResource(imageCharacter2);
                band = false;
            } else {
                imgPersonaje.setImageResource(imageCharacter);
                band = true;
            }
            handler.postDelayed(this, 3000);
        }
    };

    public boolean onKeyDown(int KeyCode, KeyEvent event){
        if (KeyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(context, "Se presiono el btn salir", Toast.LENGTH_SHORT).show();
            mediaPlayer.stop();
            startActivity(intentSelectCharacter);
            return true;
        }
        return super.onKeyDown(KeyCode, event);
    }


}
