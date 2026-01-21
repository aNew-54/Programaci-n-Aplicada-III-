package sistemas.unc.edu.pe.appsaludpersonas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import Modelo.Persona;
import VistaModelo.VMPersona;

public class MyBottomSheet extends BottomSheetDialogFragment {

    public interface OnPersonaRegistrada {
        void onPersonaGuardada(Persona persona);
    }

    private OnPersonaRegistrada listener;
    private EditText etNombres, etApellidos, etEdad, etDNI, etPeso, etAltura;
    private VMPersona oVMpersona;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnPersonaRegistrada) {
            listener = (OnPersonaRegistrada) context;
        } else {
            throw new RuntimeException(context.toString() + " debe implementar OnPersonaRegistrada");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottom_sheet, container, false);

        oVMpersona = new VMPersona();
        etNombres = view.findViewById(R.id.et_nombres);
        etApellidos = view.findViewById(R.id.et_apellidos);
        etEdad = view.findViewById(R.id.et_edad);
        etDNI = view.findViewById(R.id.et_dni);
        etPeso = view.findViewById(R.id.et_peso);
        etAltura = view.findViewById(R.id.et_altura);

        view.findViewById(R.id.b_registrar).setOnClickListener(v -> {
            try {
                String nom = etNombres.getText().toString();
                String ape = etApellidos.getText().toString();
                int edad = Integer.parseInt(etEdad.getText().toString());
                String dni = etDNI.getText().toString();
                double peso = Double.parseDouble(etPeso.getText().toString());
                double alt = Double.parseDouble(etAltura.getText().toString());

                Persona oPersona = new Persona(nom, ape, edad, dni, peso, alt);

                String mensaje = oVMpersona.Agregar(oPersona);
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();

                if (listener != null) {
                    listener.onPersonaGuardada(oPersona);
                }
                dismiss();
            } catch (Exception e) {
                Toast.makeText(getContext(), "Error: Verifique los datos ingresados", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}