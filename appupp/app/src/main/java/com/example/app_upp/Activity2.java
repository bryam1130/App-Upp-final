package com.example.app_upp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Activity2 extends AppCompatActivity {

    //Declaración de variables de los componentes EditText,.
    EditText email, contra;

    //Declaración de variables String para enviar la cadena de caracteres del usuario al archivo PHP.
    String str_email,str_password;
    String url = "https://appupp2021.000webhostapp.com/app-upp/Verificadas/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //Declaración de los componentes que vamos a utilizar.
        email = findViewById(R.id.etemail);
        contra = findViewById(R.id.etcontraseña);

    }

    //Declaramos la variable Login, para validar que los Edit Text no estén vacíos .
    public void Login(View view) {
        if(email.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese una  email", Toast.LENGTH_SHORT).show();
        }
        else if(contra.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese una clave", Toast.LENGTH_SHORT).show();
        }

        // Con este else obtenemos los datos ingresados en los ET.
        else{
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Por favor espera...");
            progressDialog.show();
            str_email = email.getText().toString().trim();
            str_password = contra.getText().toString().trim();

            // Invocamos el metodo Post  .
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    // Aqui va un mensaje si los datos son correctos, y tambien limpiamos los campos del EditText.
                    progressDialog.dismiss();
                    if(response.equalsIgnoreCase("ingresastes correctamente")){

                        email.setText("");
                        contra.setText("");

                        // Le decimos que comience una nueva activity si se ingresa correctamente.
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Toast.makeText(Activity2.this, response, Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Toast.makeText(Activity2.this, response, Toast.LENGTH_SHORT).show();
                    }

                }

                //Declaramos la variable error en el caso que los datos sean incorrectos se mostrara un texto de error.
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(Activity2.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

                    //En esta parte validamos que los parametros sean correctos .
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("email",str_email);
                    params.put("contra",str_password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Activity2.this);
            requestQueue.add(request);
        }
    }

    //Esta variable es para cambiar de actividad .
    public void moveToRegistration(View view) {
        startActivity(new Intent(getApplicationContext(), Activity3.class));
        finish();
    }
}
