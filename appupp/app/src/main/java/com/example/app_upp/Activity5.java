package com.example.app_upp;

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

public class Activity5 extends AppCompatActivity {

    //Variable para identificador de cada permiso
    static final int Request_CodePermisos = 7099;

    CheckBox checkBox_Terminos;
    Button btn_Aceptar_terminos;
    Button buton_permis;
    Button btn_modificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        checkBox_Terminos = findViewById(R.id.check_Terminos);


        buton_permis = findViewById(R.id.btnpermiso);
        btn_modificar = findViewById(R.id.btn_config_permisos);

        buton_permis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission( Activity5.this, "android.permission.ACCESS_FINE_LOCATION") +
                        ContextCompat.checkSelfPermission( Activity5.this, "android.permission.READ_CONTACTS") +
                        ContextCompat.checkSelfPermission( Activity5.this, "android.permission.SEND_SMS") +
                        ContextCompat.checkSelfPermission( Activity5.this, "android.permission.INTERNET") ==0) {
                    Toast.makeText( Activity5.this, "Permisos Concedidos", Toast.LENGTH_SHORT).show();
                }else if(ActivityCompat.shouldShowRequestPermissionRationale(Activity5.this, "android.permission.ACCESS_FINE_LOCATION") ||
                        ActivityCompat.shouldShowRequestPermissionRationale(Activity5.this, "android.permission.READ_CONTACTS") ||
                        ActivityCompat.shouldShowRequestPermissionRationale(Activity5.this, "android.permission.SEND_SMS") ||
                        ActivityCompat.shouldShowRequestPermissionRationale(Activity5.this, "android.permission.INTERNET"))
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Activity5.this);
                    builder.setTitle("Conceder Permisos de:");
                    builder.setMessage("Localization, Read Contacts, Read Messages y Internet");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Activity5.this, new String[]{"android.permission.ACCESS_FINE_LOCATION",
                                    "android.permission.READ_CONTACTS",
                                    "android.permission.SEND_SMS",
                                    "android.permission.INTERNET"},Activity5.Request_CodePermisos);
                        }
                    }) ;
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    builder.create().show();
                }   else  {
                    ActivityCompat.requestPermissions(Activity5.this, new String[]{"android.permission.ACCESS_FINE_LOCATION",
                            "android.permission.READ_CONTACTS",
                            "android.permission.SEND_SMS",
                            "android.permission.INTERNET"},Activity5.Request_CodePermisos);
                }

            }
        });

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.fromParts("package", Activity5.this.getPackageName(),null));
                startActivity(intent);
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
            Toast.makeText(Activity5.this, "Permisos Denegados..", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(Activity5.this, "Permisos Aceptados..", Toast.LENGTH_SHORT).show();

        }
    }


}

