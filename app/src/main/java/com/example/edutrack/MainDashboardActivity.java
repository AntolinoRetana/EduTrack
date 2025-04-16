package com.example.edutrack;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.edutrack.Fragments.FragmentAgregarEstudiante;
import com.example.edutrack.Fragments.FragmentHome;
import com.example.edutrack.Fragments.FragmentListaEstudiantes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainDashboardActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Fragmento inicial
        loadFragment(new FragmentHome());

        // Navegación entre fragmentos
        bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                fragment = new FragmentHome();
            } else if (id == R.id.nav_agregar) {
                fragment = new FragmentAgregarEstudiante();
            } else if (id == R.id.nav_lista) {
                fragment = new FragmentListaEstudiantes();

            }
            return loadFragment(fragment);
        });

        bottomNavigation.setSelectedItemId(R.id.nav_home);
    }

    // Cargar el fragmento
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}