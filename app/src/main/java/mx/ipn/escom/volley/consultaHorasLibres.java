package mx.ipn.escom.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class consultaHorasLibres extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<String> horarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_orientation_app);

       // if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().heightPixels) {
           // Toast.makeText(this,"Screen switched to Landscape mode",Toast.LENGTH_SHORT).show();
         //   setContentView(R.layout.activity_screen_orientation_app);
        //}
        //else{
          //  setContentView(R.layout.activity_consulta_horas_libres);
            //Toast.makeText(this,"Screen switched to Portrait mode",Toast.LENGTH_SHORT).show();
        //}

        gridView = findViewById(R.id.gridView);

        // Datos a mostrar
        horarios = new ArrayList<>();

        if(horarios.isEmpty()){
            //getHorarios();
            horarios.add("Profesor");
            horarios.add("Materia");
            horarios.add("Lun");
            horarios.add("Mar");
            horarios.add("Mie");
            horarios.add("Jue");
            horarios.add("Vie");
            getDatos();
//            Log.d("Array:","NO HAY NADA EN EL ARRAY");
        }
        //else{
/*
            horarios.add("Alejandro");
            horarios.add("Fernando");
            horarios.add("Rubén");
            horarios.add("Santiago");
            horarios.add("Alejandro");
            horarios.add("Fernando");
            horarios.add("Rubén");
            horarios.add("Santiago");
            horarios.add("Alejandro");
            horarios.add("Fernando");
            horarios.add("Rubén");
            horarios.add("Santiago");
            horarios.add("Alejandro");
            horarios.add("Fernando");
            horarios.add("Rubén");
            horarios.add("Santiago");
            horarios.add("Alejandro");
            horarios.add("Fernando");
            horarios.add("Rubén");
            horarios.add("Santiago");
            horarios.add("Alejandro");
        }
*/
        // Adaptador, la forma visual en que mostraremos nuestros datos




        // Enlazamos con nuestro adaptador personalizado
      //  MyAdapter myAdapter = new MyAdapter(this, R.layout.grid_item, names);
        //gridView.setAdapter(myAdapter);

    }
/*
    private void getHorarios() {
        String url = "http://localhost:3000/salon/horariosprofesores";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest (Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "onResponse:  " + response.toString(), Toast.LENGTH_SHORT).show();
                        //Log.d("JSON: ",response.toString());
                        showGridView(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "onEError:  " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    */

    private void getDatos() {
        //String url = "http://10.0.0.3:3000/administrador/consulta";
        String url = "http://10.100.75.246:3000/salon/horariosprofesores";
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        //String url = getResources().getString(R.string.json_get_url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "onResponse:  " + response.toString(), Toast.LENGTH_SHORT).show();
                       // Log.d("JSON: ",response.toString());
                        showGridView(response);
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

    private void showGridView(JSONObject jsonObject){
        //Looping through all the elements of json array
     //   for(int i = 0; i<jsonObject.length(); i++){
            //Creating a json object of the current index



            try {
                JSONArray jsonarray = jsonObject.getJSONArray("dato");
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonObject = jsonarray.getJSONObject(i);
                    horarios.add(jsonObject.getString("nombre"));
                    horarios.add(jsonObject.getString("nombreMateria"));
                    horarios.add(jsonObject.getString("lunes"));
                    horarios.add(jsonObject.getString("martes"));
                    horarios.add(jsonObject.getString("miercoles"));
                    horarios.add(jsonObject.getString("jueves"));
                    horarios.add(jsonObject.getString("viernes"));
//                    Log.d("Nombre",jsonObject.getString("nombre"));


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, horarios);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(consultaHorasLibres.this, "Clicked: "+horarios.get(position), Toast.LENGTH_LONG).show();
            }
        });

        // Enlazamos el adaptador con nuestro List View
        gridView.setAdapter(adapter);

        // }
    }
}
