package com.example.edutrack.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.edutrack.Dao.EstudianteDao;
import com.example.edutrack.Dao.UsuarioDao;
import com.example.edutrack.Moduls.Estudiante;
import com.example.edutrack.Moduls.Usuario;

// Aquí registramos todas las entidades que usarán Room
@Database(entities = {Usuario.class, Estudiante.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UsuarioDao usuarioDao();
    public abstract EstudianteDao estudianteDao(); // <--- agregar esto

    // Singleton para no crear muchas instancias
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "edutrack_db"
            ).fallbackToDestructiveMigration()
           .allowMainThreadQueries().build(); // Solo para pruebas: queries en el hilo principal

        }
        return instance;
    }
}
