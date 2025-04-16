package com.example.edutrack.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.edutrack.DB.AppDatabase;
import com.example.edutrack.Moduls.Usuario;
import com.example.edutrack.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNuevoUsuario, etNuevoPassword;
    private Button btnRegistrar, btnVolverLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //

        etNuevoUsuario = findViewById(R.id.etNuevoUsuario);
        etNuevoPassword = findViewById(R.id.etNuevoPassword);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnVolverLogin = findViewById(R.id.btnVolverLogin);

        // Botón para registrar nuevo usuario
        btnRegistrar.setOnClickListener(v -> {
            String nombre = etNuevoUsuario.getText().toString().trim();
            String clave = etNuevoPassword.getText().toString().trim();

            if (nombre.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Usuario nuevoUsuario = new Usuario(nombre, clave);
            AppDatabase.getInstance(this).usuarioDao().insertar(nuevoUsuario);

            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

            // Volver a Login después del registro
            startActivity(new Intent(this, LoginActivity.class));

            finish();
        });

        // Botón para volver al login sin registrar
        btnVolverLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        //
    }
}