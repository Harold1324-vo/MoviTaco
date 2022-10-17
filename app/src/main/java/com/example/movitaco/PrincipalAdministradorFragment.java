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

public class PrincipalAdministradorFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_principal_administrador, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        Button btnAlimentos = view.findViewById(R.id.btnAgregarAlimentos);
        Button btnBebidas = view.findViewById(R.id.btnAgregarBebidas);
        Button btnMeseros = view.findViewById(R.id.btnAgregarMeseros);
        Button btnReportes = view.findViewById(R.id.btnGenerarReportes);

        btnAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_principalAdministradorFragment_to_alimentosAdministradorFragment);
            }
        });

        btnBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_principalAdministradorFragment_to_bebidasAdministradoFragment);
            }
        });

        btnMeseros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_principalAdministradorFragment_to_meserosAdministradorFragment);
            }
        });

        btnReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_principalAdministradorFragment_to_reportesAdministradorFragment);
            }
        });
    }
}