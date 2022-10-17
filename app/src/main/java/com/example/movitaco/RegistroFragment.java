package com.example.movitaco;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class RegistroFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        Button btnRegistro;
        EditText nombre, correo, telefono, direccion, contrasena, validarContraseña;

        btnRegistro = v.findViewById(R.id.btnRegistrar);
        nombre = v.findViewById(R.id.edtTaqueria);
        correo = v.findViewById(R.id.edtCorreo);
        direccion = v.findViewById(R.id.edtDireccion);
        telefono = v.findViewById(R.id.edtTelefono);
        contrasena = v.findViewById(R.id.edtContraseña);
        validarContraseña = v.findViewById(R.id.edtConfirmarContraseña);

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Usuario = nombre.getText().toString();
                String Correo = correo.getText().toString();
                String Telefono = telefono.getText().toString();
                String Direccion = direccion.getText().toString();
                String Contrasena = contrasena.getText().toString();
                String Validar = validarContraseña.getText().toString();

                //Declarar una URL de tipo string, pasarle la variable y con su contenido
                String url = "http://172.16.20.111/apiConsumoTaqueria.php?nombreTaqueria="+Usuario+"&correoElectronico="+Correo+"&telefonoTaqueria="+Telefono+"&direccion="+Direccion+"&contrasenaTaqueria="+Contrasena;

                if(Usuario.isEmpty()){
                    nombre.setError("Ingresa un nombre");
                }else if(validarNombre(Usuario)){
                    nombre.setError("El nombre es muy largo");
                }

                if(Correo.isEmpty()){
                    correo.setError("Ingresa un email");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(Correo).matches()){
                    correo.setError("Email incorrecto");
                }

                if(Telefono.isEmpty()){
                    telefono.setError("Ingresa un teléfono");
                }else if(validarTelefono(Telefono) == false){
                    telefono.setError("El número telefónico no debe tener letras");
                }

                if(Direccion.isEmpty()){
                    direccion.setError("Ingresa una dirección");
                }else if(validarDireccion(Direccion) == false){
                    direccion.setError("La dirección es muy extensa");
                }

                if(Contrasena.isEmpty()){
                    contrasena.setError("Ingresa una contraseña");
                }else if(Contrasena.length() > 20){
                    contrasena.setError("La contraseña es muy larga");
                }else if(validarContraseña(Contrasena) == false){
                    contrasena.setError("La contraseña debe tener letras, números y carácter especial");
                }

                if(Validar.isEmpty()){
                    validarContraseña.setError("Verifique contraseña");
                }else if(Contrasena.equals(Validar)){
                    Contrasena = getMD5(Contrasena);
                }else{
                    validarContraseña.setError("Las contraseñas no coinciden");
                }
                Contrasena = getMD5(Contrasena);
                if((!Usuario.isEmpty() && validarNombre(Usuario)) && Patterns.EMAIL_ADDRESS.matcher(Correo).matches() && !Telefono.isEmpty() && !Direccion.isEmpty() && !Contrasena.isEmpty() && !Validar.isEmpty()){
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(v.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                            Navigation.findNavController(v).navigate(R.id.action_registroFragment_to_inicioSesionFragment);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(v.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(jsonObjectRequest);
                }
            }
        });
    }

    boolean validarNombre(String nombre){
        if(nombre.length() < 25){
            return false;
        }
        return true;
    }

    boolean validarTelefono(String telefono){
        for(int x=0;x<telefono.length();x++){
            char c = telefono.charAt(x);
            if(telefono.length() != 10 || (c >= 'a' && c<= 'z') || (c >= 'A' && c<= 'Z') || c == ' ' || c == '-' || c == '.' || c == '(' || c == '/'
                    || c == ')' || c == 'N' || c == ',' || c == '*' || c == ';' || c == '#' || c == '+'){
                return false;
            }
        }
        return true;
    }

    boolean validarDireccion(String direccion){
        if(direccion.length() > 250){
            return false;
        }
        return true;
    }

    boolean validarContraseña(String contraseña){
        boolean numeros = false;
        boolean letras = false;
        for(int x=0;x<contraseña.length();x++){
            char c = contraseña.charAt(x);
            //Si no está entre a y z, ni entre A y Z, ni es un espacio
            if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == 'ñ' || c == 'Ñ' || c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú'
                    || c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó'  || c == 'Ú' || c == '@' || c == '#' || c == '$' || c == '_' || c == '-'
                    || c == '&' || c == '*'){
                letras = true;
            }
            if((c >= '0' && c <= '9')){
                numeros = true;
            }
        }
        if(numeros == true && letras == true){
            return true;
        }
        return true;
    }

    static String getMD5(String s){
        try{
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for(byte aMessageDigest : messageDigest)
                hexString.append(Integer.toHexString(0xFF & aMessageDigest));
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}