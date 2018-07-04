package com.clair.ecda.pid_vecindad;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText etUsuario, etPassword;
    Button btnIngresar;
    ImageView imagenVecindad;
    Context context;
    private String usuario, password, usuarioGuardado = "Erick", passwordGuardado = "12";
    //Para iniciar la nueva actividad
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = Login.this;

        etUsuario = (EditText)findViewById(R.id.edt_load_usuario);
        etPassword = (EditText)findViewById(R.id.edt_load_password);
        btnIngresar = (Button)findViewById(R.id.btn_load_ingresar);
        imagenVecindad = (ImageView)findViewById(R.id.img_load_lavecindad);
        //Se define el Intent
        intent = new Intent(Login.this, SeleccionPersonaje.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = etUsuario.getText().toString();
                password = etPassword.getText().toString();

                if (usuario.length() == 0 || password.length() == 0){
                    Toast.makeText(context, "Favor de ingredar los datos para continuar", Toast.LENGTH_SHORT).show();
                } else {
                    if (usuario.equals(usuarioGuardado)){
                        if (password.equals(passwordGuardado)){
                            //Toast.makeText(context, "¡Acceso correcto!", Toast.LENGTH_SHORT).show();
                            startActivity(intent); // Inicio de la actividad
                        } else {
                            Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Usuario no encontrado, intenta de nuevo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        /*
        imagenVecindad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Has tocado la imágen", Toast.LENGTH_SHORT).show();
            }
        });
        */
    }

}
