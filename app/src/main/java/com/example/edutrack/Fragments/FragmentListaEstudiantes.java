package com.example.edutrack.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edutrack.Adapter.EstudianteAdapter;
import com.example.edutrack.DB.AppDatabase;
import com.example.edutrack.Moduls.Estudiante;
import com.example.edutrack.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListaEstudiantes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListaEstudiantes extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerEstudiantes;
    private EstudianteAdapter adapter;

    public FragmentListaEstudiantes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListaEstudiantes.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListaEstudiantes newInstance(String param1, String param2) {
        FragmentListaEstudiantes fragment = new FragmentListaEstudiantes();
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
        View vista = inflater.inflate(R.layout.fragment_lista_estudiantes, container, false);

        // Referenciar RecyclerView
        recyclerEstudiantes = vista.findViewById(R.id.recyclerEstudiantes);
        recyclerEstudiantes.setLayoutManager(new LinearLayoutManager(getContext()));

        // Obtener datos
        List<Estudiante> lista = AppDatabase
                .getInstance(getContext())
                .estudianteDao()
                .obtenerTodos();

        // Enlazar con el adapter
        adapter = new EstudianteAdapter(lista, getContext());
        recyclerEstudiantes.setAdapter(adapter);

        return vista;    }
}