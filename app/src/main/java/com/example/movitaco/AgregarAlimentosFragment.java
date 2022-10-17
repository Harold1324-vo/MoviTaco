package com.example.movitaco;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class AgregarAlimentosFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{
    EditText idTaqueria, nombreProducto, precioProducto;
    Button btnRegistrar;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public AgregarAlimentosFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_agregar_alimentos, container, false);
        idTaqueria = (EditText) v.findViewById(R.id.etIdProducto);
        nombreProducto = (EditText) v.findViewById(R.id.etNombreProducto);
        precioProducto = (EditText) v.findViewById(R.id.etPrecioProducto);
        btnRegistrar = (Button) v.findViewById(R.id.btnActualizar);

        request = Volley.newRequestQueue(getContext());

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });
        return v;
    }

    private void cargarWebService() {

        progress = new ProgressDialog(getContext());
        progress.setMessage("Cargando...");
        progress.show();

        String url = "http://172.16.20.111/apiRegistrarProducto.php?idTaqueria=" + idTaqueria.getText().toString() +
                "&nombreProducto=" + nombreProducto.getText().toString() + "&precioProducto=" + precioProducto.getText().toString();
        url = url.replace(" ","%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }
    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Se registró exitosamente", Toast.LENGTH_SHORT).show();
        progress.hide();
        idTaqueria.setText("");
        nombreProducto.setText("");
        precioProducto.setText("");
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        progress.hide();
        Toast.makeText(getContext(), "No se logró registrar" + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }
}