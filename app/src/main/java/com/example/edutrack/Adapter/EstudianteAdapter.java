package com.example.edutrack.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edutrack.DB.AppDatabase;
import com.example.edutrack.Fragments.FragmentEditarEstudiante;
import com.example.edutrack.Moduls.Estudiante;
import com.example.edutrack.R;

import java.util.List;

public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder> {
    private List<Estudiante> listaEstudiantes;
    private Context context;
    public EstudianteAdapter(List<Estudiante> listaEstudiantes, Context context) {
        this.listaEstudiantes = listaEstudiantes;
        this.context = context;
    }

    @NonNull
    @Override
    public EstudianteAdapter.EstudianteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_estudiante, parent, false);
        return new EstudianteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudianteAdapter.EstudianteViewHolder holder, int position) {
        Estudiante est = listaEstudiantes.get(position);

        holder.tvNombre.setText(est.getNombre());
        holder.tvCurso.setText("Curso: " + est.getCurso());
        holder.tvNota.setText("Nota final: " + est.getNotaFinal());
        holder.tvEstado.setText(est.getEstado());

        // Colores dinámicos para aprobado/reprobado
        if (est.getEstado().equalsIgnoreCase("Aprobado")) {
            holder.tvEstado.setTextColor(0xFF4CAF50); // verde
        } else {
            holder.tvEstado.setTextColor(0xFFF44336); // rojo
        }

        // 🔥 CLIC EN ELIMINAR
        holder.btnEliminar.setOnClickListener(v -> {
            new android.app.AlertDialog.Builder(context)
                    .setTitle("¿Eliminar estudiante?")
                    .setMessage("¿Estás seguro de que deseas eliminar a " + est.getNombre() + "?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        // Eliminar de Room
                        AppDatabase.getInstance(context).estudianteDao().eliminar(est);
                        // Actualizar lista local
                        listaEstudiantes.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, listaEstudiantes.size());
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        // ✏️ CLIC EN EDITAR (próximo paso)
        holder.btnEditar.setOnClickListener(v -> {
            FragmentEditarEstudiante fragmentEditar = new FragmentEditarEstudiante(est);
            ((AppCompatActivity) context).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragmentEditar)
                    .addToBackStack(null)
                    .commit();
        });

    }

    @Override
    public int getItemCount() {
        return listaEstudiantes.size();
    }

    public class EstudianteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCurso, tvNota, tvEstado;
        ImageView btnEditar, btnEliminar;
        public EstudianteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCurso = itemView.findViewById(R.id.tvCurso);
            tvNota = itemView.findViewById(R.id.tvNota);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
