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

public class PrincipalMeseroFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_principal_mesero, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        Button btnVentaLocal = view.findViewById(R.id.btnVentaLocal);
        Button btnVentaExterna = view.findViewById(R.id.btnVentaExterna);
        Button btnCuenta = view.findViewById(R.id.btnCuenta);

        btnVentaLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_principalMeseroFragment_to_ventaLocalFragment);
            }
        });

        btnVentaExterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_principalMeseroFragment_to_ventaExternaFragment);
            }
        });

        btnCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_principalMeseroFragment_to_cuentaMeseroFragment);
            }
        });
    }
}