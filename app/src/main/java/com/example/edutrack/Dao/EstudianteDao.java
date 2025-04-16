package com.example.edutrack.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.edutrack.Moduls.Estudiante;

import java.util.List;

@Dao
public interface EstudianteDao {
    // Aquí van las operaciones CRUD para la entidad Estudiante
    // Insertar un nuevo estudiante
    @Insert
    void insertar(Estudiante estudiante);

    // Obtener todos los estudiantes
    @Query("SELECT * FROM estudiantes")
    List<Estudiante> obtenerTodos();

    // Buscar un estudiante por ID
    @Query("SELECT * FROM estudiantes WHERE id = :id")
    Estudiante obtenerPorId(int id);

    // Actualizar un estudiante existente
    @Update
    void actualizar(Estudiante estudiante);

    // Eliminar un estudiante
    @Delete
    void eliminar(Estudiante estudiante);
}
