package mx.ipn.escom.volley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class consultaUsuariosActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonVerDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_usuarios);

        buttonVerDatos = findViewById(R.id.buttonVer);
        buttonVerDatos.setOnClickListener(this);

    }

    private void getDatos() {
        //String url = "http://10.0.0.3:3000/administrador/consulta";
        String url = "http://10.0.0.3:3000/ciclo/tipoeducacionciclo";
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        //String url = getResources().getString(R.string.json_get_url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "onResponse:  " + response.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("JSON: ",response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "onEError:  " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        jsonObjectRequest.setTag(REQ_TAG);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonVerDatos) {
            // cargarDatos();
            getDatos();
        }
    }
}


