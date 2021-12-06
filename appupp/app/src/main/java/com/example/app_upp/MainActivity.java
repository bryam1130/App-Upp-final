package com.example.app_upp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    //Variable para identificador de cada permiso
    static final int Request_CodePermisos = 7099;

    CheckBox checkBox_Terminos;
    Button btn_Aceptar_terminos;
    Button buton_permis;
    Button btn_modificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox_Terminos = findViewById(R.id.check_Terminos);

        btn_Aceptar_terminos = findViewById(R.id.btn_aceptar);
        btn_Aceptar_terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

        buton_permis = findViewById(R.id.btnpermiso);

        buton_permis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission( MainActivity.this, "android.permission.ACCESS_FINE_LOCATION") +
                        ContextCompat.checkSelfPermission( MainActivity.this, "android.permission.READ_CONTACTS") +
                        ContextCompat.checkSelfPermission( MainActivity.this, "android.permission.SEND_SMS") +
                        ContextCompat.checkSelfPermission( MainActivity.this, "android.permission.INTERNET") ==0) {
                    Toast.makeText( MainActivity.this, "Permisos Concedidos", Toast.LENGTH_SHORT).show();


                }else if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, "android.permission.ACCESS_FINE_LOCATION") ||
                        ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, "android.permission.READ_CONTACTS") ||
                        ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, "android.permission.SEND_SMS") ||
                        ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, "android.permission.INTERNET"))
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Conceder Permisos de:");
                    builder.setMessage("Localization, Read Contacts, Read Messages y Internet");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION",
                                    "android.permission.READ_CONTACTS",
                                    "android.permission.SEND_SMS",
                                    "android.permission.INTERNET"},MainActivity.Request_CodePermisos);
                        }
                    }) ;
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    builder.create().show();
                }   else  {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION",
                            "android.permission.READ_CONTACTS",
                            "android.permission.SEND_SMS",
                            "android.permission.INTERNET"},MainActivity.Request_CodePermisos);
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != Request_CodePermisos){
            return;
        }
        if (grantResults.length <=0 || grantResults[0] +
                grantResults[1] +
                grantResults[2] +
                grantResults[3] !=0){
            Toast.makeText(MainActivity.this, "Permisos Denegados..", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "Permisos Aceptados..", Toast.LENGTH_SHORT).show();

        }
    }

    private void validar() {
        if (checkBox_Terminos.isChecked()) {
            Toast.makeText(this, "Terminos Aceptados", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),Activity9.class));

        } else {
            Toast.makeText(this, "Aceptar Terminos Por favor", Toast.LENGTH_SHORT).show();
        }
    }

}
