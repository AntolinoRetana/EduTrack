package com.example.edutrack.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.edutrack.Moduls.Usuario;

@Dao
public interface UsuarioDao {
    @Insert
    void insertar(Usuario usuario);

    // Consulta para iniciar sesión
    @Query("SELECT * FROM usuarios WHERE nombreUsuario = :nombre AND contrasena = :clave LIMIT 1")
    Usuario login(String nombre, String clave);
}
