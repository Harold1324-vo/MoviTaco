package com.example.movitaco;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class InicioSesionFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio_sesion, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        Button btnAdministrador = view.findViewById(R.id.btnIniciarSesionAdministrador);
        Button btnMesero = view.findViewById(R.id.btnIniciarSesionMesero);
        TextView bntRegistrar = view.findViewById(R.id.txtRegistro);


        btnAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_inicioSesionFragment_to_principalAdministradorFragment);
            }
        });

        btnMesero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_inicioSesionFragment_to_principalMeseroFragment);
            }
        });

        bntRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_inicioSesionFragment_to_registroFragment);
            }
        });
    }
}