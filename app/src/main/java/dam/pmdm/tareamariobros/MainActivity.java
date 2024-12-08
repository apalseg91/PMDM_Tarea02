package dam.pmdm.tareamariobros;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import java.util.Locale;
import dam.pmdm.tareamariobros.databinding.ActivityMainBinding;
import dam.pmdm.tareamariobros.databinding.CharacterDetailFragmentBinding;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private ActionBarDrawerToggle toggle;
    private ActivityMainBinding binding;
    Switch switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //declaro e inicializo el binding para inflar la pantalla
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //incializo y configuro el navcotroller buscando por id en el activity_main el fragment
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //creo la toolbar y le digo que se va a gestionar por el grafo de navegacion
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        //establecemos la navegacion entre fragmentos
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);

        // Configurar menú toggle
        configureToggleMenu();

        // Configurar la navegación
        configureNavigation();

        // Configurar el icono del menú en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //inicializamos el boton switch del cambio de idioma
        NavigationView navigationView = findViewById(R.id.nav_view);//localizamos la vista de navegacion
        View headerView = navigationView.getHeaderView(0); // Obtener la vista del encabezado
        switchButton = headerView.findViewById(R.id.switch_button); // Encontrar el Switch por su ID

        // Configurar el Switch según el idioma guardado
        setSwitchLanguageState();

        // Configurar el listener para cambiar el idioma
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Solo cambio el idioma si realmente se ha modificado el estado del Switch
                SharedPreferences preferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
                String currentLanguage = preferences.getString("language", "es");

                // Si el idioma es español y el Switch esta activado (cambiando a ingles)
                if ("es".equals(currentLanguage) && isChecked) {
                    changeLanguage("en"); // Cambiar a inglés
                    Toast.makeText(MainActivity.this, R.string.change_en, Toast.LENGTH_SHORT).show();
                }
                // Si el idioma es ingles y el Switch esta desactivado (cambiando a español)
                else if ("en".equals(currentLanguage) && !isChecked) {
                    changeLanguage("es"); // Cambiar a español
                    Toast.makeText(MainActivity.this, R.string.change_sp, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //metodo que controla el click en el boton de la appbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Manejar el clic en el menú
        int id = item.getItemId();
        if (id == R.id.menu_item) {
            // Mostrar el AlertDialog
            showAboutDialog();
        }
        // Manejar clics en el icono del menú
        else if (toggle.onOptionsItemSelected(item)) {
            // return true;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //metodo que infla el boton de la appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú
        getMenuInflater().inflate(R.menu.acerca_de, menu);
        return true;
    }

    //metodo para manejar el click en un personaje, me viene del adaptador
    public void characterClicked(CharacterData character, View view) {
        //creo un objeto Bundle pasar pasar datos entre fragments o activities (al detailFragment)
        Bundle bundle = new Bundle();
        bundle.putString("name", character.getName());
        bundle.putString("skill", character.getSkill());
        bundle.putString("description", character.getDescription());
        bundle.putInt("image", character.getImage());
        //llamo a Navigation y le envio la info del bundle al fragment o vista que le paso(detailFrgmnt)
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }

    //metodo que implementa el boton Back con el NavController
    @Override
    public boolean onSupportNavigateUp() {
        //utiliza el metodo navigateup() del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    //manejo del boton de la appbar
    private void showAboutDialog() {
        // Crear y mostrar el AlertDialog
        new AlertDialog.Builder(this)
                .setTitle(R.string.menu_title)
                .setMessage(R.string.menu_message)
                .setIcon(R.drawable.icono) // Ícono de la app
                .setPositiveButton(R.string.accept, null) // Botón para cerrar
                .show();
    }

    private void configureToggleMenu() {
        // Configurar el ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                R.string.open_menu,
                R.string.close_menu
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    //metodo que configura la navegación entre fragments
    private void configureNavigation() {
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Manejar la selección de elementos del menú
        binding.navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.nav_home) {
                navController.navigate(R.id.mainFragment); // Navegar al fragmento de inicio
            }
            binding.drawerLayout.closeDrawers(); // Cerrar el menú
            return true;
        });
    }

    // Método para cambiar el idioma de la app
    private void changeLanguage(String languageCode) {
        // Crear configuración para el nuevo idioma
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        // Configurar la nueva configuración de idioma
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = locale;

        // Aplicar la nueva configuración
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Guardar el idioma en SharedPreferences
        SharedPreferences preferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", languageCode);
        editor.apply();
        // Reiniciar la actividad para aplicar el cambio de idioma
        recreate();

    }

    // Método para configurar el estado del Switch según el idioma guardado
    private void setSwitchLanguageState() {
        // Obtener el idioma guardado de SharedPreferences
        SharedPreferences preferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
        String language = preferences.getString("language", "es"); // Por defecto, español
        // Si el idioma guardado es inglés, el Switch debe estar activado
        if ("en".equals(language)) {
            switchButton.setChecked(true);
        } else {
            switchButton.setChecked(false);
        }
    }

}