package com.example.edutrack.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputFilter;
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
 * Use the {@link FragmentEditarEstudiante#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEditarEstudiante extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText etNombre, etEdad, etCurso, etNota;
    private Button btnActualizar;
    private Estudiante estudiante;

    public FragmentEditarEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public FragmentEditarEstudiante() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEditarEstudiante.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEditarEstudiante newInstance(String param1, String param2) {
        FragmentEditarEstudiante fragment = new FragmentEditarEstudiante();
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
        View vista = inflater.inflate(R.layout.fragment_editar_estudiante, container, false);

        etNombre = vista.findViewById(R.id.etNombreEditar);
        etEdad = vista.findViewById(R.id.etEdadEditar);
        etCurso = vista.findViewById(R.id.etCursoEditar);
        etNota = vista.findViewById(R.id.etNotaEditar);
        btnActualizar = vista.findViewById(R.id.btnActualizar);

        // Prellenar campos
        etNombre.setText(estudiante.getNombre());
        etEdad.setText(String.valueOf(estudiante.getEdad()));
        etCurso.setText(estudiante.getCurso());
        etNota.setText(String.valueOf(estudiante.getNotaFinal()));

        // Configurar el filtro para que el campo nombre solo acepte letras
        InputFilter letrasSoloFilter = (source, start, end, dest, dstart, dend) -> {
            for (int i = start; i < end; i++) {
                if (!Character.isLetter(source.charAt(i)) && source.charAt(i) != ' ') {
                    // Permitimos espacios para nombres compuestos
                    Toast.makeText(getContext(), "Solo se permiten letras en el nombre", Toast.LENGTH_SHORT).show();
                    return "";
                }
            }
            return null; // Acepta el carácter
        };

        // Aplicar el filtro al campo de nombre
        etNombre.setFilters(new InputFilter[]{letrasSoloFilter});

        btnActualizar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String curso = etCurso.getText().toString().trim();
            String edadStr = etEdad.getText().toString().trim();
            String notaStr = etNota.getText().toString().trim();

            if (nombre.isEmpty() || curso.isEmpty() || edadStr.isEmpty() || notaStr.isEmpty()) {
                Toast.makeText(getContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int edad = Integer.parseInt(edadStr);
            double nota = Double.parseDouble(notaStr);
            String estado = (nota >= 6.0) ? "Aprobado" : "Reprobado";

            estudiante.setNombre(nombre);
            estudiante.setCurso(curso);
            estudiante.setEdad(edad);
            estudiante.setNotaFinal(nota);
            estudiante.setEstado(estado);

            AppDatabase.getInstance(getContext()).estudianteDao().actualizar(estudiante);

            Toast.makeText(getContext(), "Estudiante actualizado", Toast.LENGTH_SHORT).show();

            getActivity().getSupportFragmentManager().popBackStack();
        });

        return vista;    }
}