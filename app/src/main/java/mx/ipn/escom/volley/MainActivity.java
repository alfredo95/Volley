package mx.ipn.escom.volley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/*
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
*/

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText editTextUserName, editTextProfession;
    private Button buttonRegister;
    private Button buttonConsultar;
    private ProgressDialog progressDialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUserName = findViewById(R.id.txtNombre);
        editTextProfession = findViewById(R.id.txtProfesion);

        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);

        buttonConsultar = findViewById(R.id.buttonConsultar);
        buttonConsultar.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
    }


    private void registerUser() {
        final String username = editTextUserName.getText().toString();
        final String profession = editTextProfession.getText().toString();
        String url = "http://10.0.0.3:3000/administrador/regis";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject json = new JSONObject();
        try {
            json.put("nombre",username);
            json.put("profesion",profession);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //String url = getResources().getString(R.string.json_get_url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "onResponse:  " + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "onResponse:  " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        jsonObjectRequest.setTag(REQ_TAG);
        requestQueue.add(jsonObjectRequest);
    }




    private void cargarDatos() {

        String url = "http://10.0.0.3/bdvolley/registro.php?nombre="+editTextUserName.getText().toString()+"&profesion="+editTextProfession.getText().toString();
 //String url = "http://10.0.0.3:3000/administrador/regis";
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="http://myserveraddress";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(getApplicationContext(), "onResponse:  " + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                        Toast.makeText(getApplicationContext(), "onErrorResponse:  " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })


        {
/*
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idUsuario", "1");
                params.put("nombre", "Hola");
                params.put("profesion", "adios");
                return params;
            }
        */
        };

        queue.add(strRequest);
        Toast.makeText(getApplicationContext(), "Response queue:  " + strRequest.toString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View view) {
        if (view==buttonRegister){
           // cargarDatos();
            registerUser();
        }
        else if (view ==buttonConsultar) {
            Intent intent = new Intent(this, consultaUsuariosActivity.class);
            //EditText editText = (EditText) findViewById(R.id.editText);
            //String message = editText.getText().toString();
          //  intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }
}
