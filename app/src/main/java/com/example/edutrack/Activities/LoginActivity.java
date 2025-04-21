package com.example.edutrack.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.edutrack.DB.AppDatabase;
import com.example.edutrack.MainDashboardActivity;
import com.example.edutrack.Moduls.Usuario;
import com.example.edutrack.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario, etPassword;
    private Button btnLogin, btnIrRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //

        // Referencias a los elementos del layout
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnIrRegistro = findViewById(R.id.btnIrRegistro);


        // Configurar el filtro para que solo acepte letras
        InputFilter letrasSoloFilter = (source, start, end, dest, dstart, dend) -> {
            for (int i = start; i < end; i++) {
                if (!Character.isLetter(source.charAt(i))) {
                    // Mostrar un pequeño mensaje informativo
                    Toast.makeText(LoginActivity.this, "Solo se permiten letras", Toast.LENGTH_SHORT).show();
                    return "";
                }
            }
            return null; // Acepta el carácter
        };

        // Aplicar el filtro al campo de usuario
        etUsuario.setFilters(new InputFilter[]{letrasSoloFilter});

        // Botón de iniciar sesión
        btnLogin.setOnClickListener(v -> {
            String nombre = etUsuario.getText().toString().trim();
            String clave = etPassword.getText().toString().trim();

            if (nombre.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show();
            } else {
                Usuario usuario = AppDatabase.getInstance(this).usuarioDao().login(nombre, clave);
                if (usuario != null) {
                    // Guardar el ID del usuario en SharedPreferences
                    saveUserId(usuario.getId());
                    // Ir al menú principal
                    startActivity(new Intent(this, MainDashboardActivity.class));
                    finish(); // No volver atrás
                } else {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Botón para ir a registrar
        btnIrRegistro.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        //
    }

    // Método para guardar el ID del usuario en SharedPreferences
    private void saveUserId(int userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
    }
}