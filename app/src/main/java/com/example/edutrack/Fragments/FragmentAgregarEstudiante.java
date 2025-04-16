package com.example.edutrack.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edutrack.DB.AppDatabase;
import com.example.edutrack.Moduls.Estudiante;
import com.example.edutrack.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAgregarEstudiante#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAgregarEstudiante extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText etNombre, etEdad, etCurso, etNota;
    private Button btnGuardar;

    public FragmentAgregarEstudiante() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAgregarEstudiante.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAgregarEstudiante newInstance(String param1, String param2) {
        FragmentAgregarEstudiante fragment = new FragmentAgregarEstudiante();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_agregar_estudiante, container, false);

        // Referenciar elementos del layout
        etNombre = vista.findViewById(R.id.etNombre);
        etEdad = vista.findViewById(R.id.etEdad);
        etCurso = vista.findViewById(R.id.etCurso);
        etNota = vista.findViewById(R.id.etNota);
        btnGuardar = vista.findViewById(R.id.btnGuardar);

        // Botón para guardar estudiante
        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String curso = etCurso.getText().toString().trim();
            String edadStr = etEdad.getText().toString().trim();
            String notaStr = etNota.getText().toString().trim();

            // Validación
            if (nombre.isEmpty() || curso.isEmpty() || edadStr.isEmpty() || notaStr.isEmpty()) {
                Toast.makeText(getContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int edad = Integer.parseInt(edadStr);
            double nota = Double.parseDouble(notaStr);

            if (edad < 4 || nota < 0 || nota > 10) {
                Toast.makeText(getContext(), "Edad o nota fuera de rango", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calcular estado
            String estado = (nota >= 6.0) ? "Aprobado" : "Reprobado";

            // Crear e insertar estudiante
            Estudiante nuevo = new Estudiante(nombre, edad, curso, nota, estado);
            AppDatabase.getInstance(getContext()).estudianteDao().insertar(nuevo);

            Toast.makeText(getContext(), "Estudiante guardado", Toast.LENGTH_SHORT).show();

            // Limpiar campos
            etNombre.setText("");
            etEdad.setText("");
            etCurso.setText("");
            etNota.setText("");
        });

        return vista;    }
}