package com.example.app_upp;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


public class Activity3 extends AppCompatActivity {
    //Declaración de variables de los componentes  a utilizar,.
    EditText txtName, txtApellido, txtEdad, txtDireccion, txtEmail,usuarios, contra;
    Button btn_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        //Declaración de los componentes que vamos a utilizar.
        txtName     = findViewById(R.id.ednombre);
        txtApellido = findViewById(R.id.edapellido);
        txtEdad     = findViewById(R.id.etedad);
        txtDireccion  = findViewById(R.id.direccion);
        txtEmail    = findViewById(R.id.etemail);
        btn_insert = findViewById(R.id.btn_register);
        usuarios = findViewById(R.id.etusuario);
        contra = findViewById(R.id.etcontraseña);


       //aquí declaramos el método para el botón registrarse.
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //aquí llamamos al metodo insertData.
                insertData();
            }
        });
    }

    //Variable para el metodo insertar datos, los convertirmos a String  y el trim elimina los espacios en blanco de estos mismos.
    private void insertData() {
        final String nombre = txtName.getText().toString().trim();
        final String apellido = txtApellido.getText().toString().trim();
        final String edad = txtEdad.getText().toString().trim();
        final String direccion = txtDireccion.getText().toString().trim();
        final String email = txtEmail.getText().toString().trim();
        final String password = contra.getText().toString().trim();
        final String usuario = usuarios.getText().toString().trim();

       //Dialogo de progreso que se muestra cuando los datos están siendo enviados.
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        //Validamos que los campos de los txt no se encuentren vacíos.
        if(nombre.isEmpty()){
            txtName.setError("complete los campos");
            return;
        }
        else if(email.isEmpty()){
            txtEmail.setError("complete los campos");
            return;
        }

         //Aquí los datos se envían hacia la URL del archivo PHP.
        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://appupp2021.000webhostapp.com/app-upp/registro.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Datos insertados")){
                                limpiar();
                                Toast.makeText(Activity3.this, "Datos insertados", Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();
                            }

                            else{
                                Toast.makeText(Activity3.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }

                        //Declaramos la variable error en el caso que los datos sean incorrectos se mostrara un texto de error.
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Activity3.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

                    //En esta parte validamos que los parametros sean correctos .
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("nombre",nombre);
                    params.put("apellido",apellido);
                    params.put("edad",edad);
                    params.put("direccion",direccion);
                    params.put("email",email);
                    params.put("contra",password);
                    params.put("usuario",usuario);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Activity3.this);
            requestQueue.add(request);
        }
    }

    //Con esta variable cerramos la actividad si se presiona el botón de regreso.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //Con esto se limpian los txt.
    public void limpiar(){
        Intent intent=new Intent(Activity3.this,MainActivity.class);
        startActivity(intent);
        txtName.setText("");
        txtEmail.setText("");
    }

    //Variable que comienza otra actividad .
    public void moveToLogin(View view){
        Intent intent=new Intent(Activity3.this,Activity2.class);
        startActivity(intent);
    }
}
