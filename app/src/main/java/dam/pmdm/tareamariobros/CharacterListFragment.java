package dam.pmdm.tareamariobros;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import dam.pmdm.tareamariobros.databinding.CharacterListFragmentBinding;
import dam.pmdm.tareamariobros.CharacterData;

public class CharacterListFragment extends Fragment {
    private CharacterListFragmentBinding binding;
    private ArrayList<CharacterData> characters;
    private CharacterRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //inflar el layout usando el binding
        binding = CharacterListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);
        Snackbar.make(view, R.string.snack_msg, Snackbar.LENGTH_SHORT).show();

        //inicializo la lista de personajes
        loadCharacters();
        //Configurar el rEcyvlerVIew
        adapter = new CharacterRecyclerViewAdapter(characters, getActivity());
        binding.characterRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.characterRecyclerview.setAdapter(adapter);
    }
    //metodo que carga la info de los personajes
    private void loadCharacters(){
        characters = new ArrayList<>();
        characters.add(new CharacterData("Mario","SuperSalto","Heroe del videojuego",R.drawable.mario));

        characters.add(new CharacterData("Luigi","Lanza bolas de fuego","Hermano del heroe del videojuego",R.drawable.luigi));

        characters.add(new CharacterData("Peach","Flota en el aire","Princesa del reino Champinon",R.drawable.princesapeach));

        characters.add(new CharacterData("Yoshi","Lanza Caparazones","Fiel compañer de Mario y Luigi",R.drawable.yoshi_));

        characters.add(new CharacterData("Toadette","DobleSalto","Hermana menor de Toad",R.drawable.toadette_));

        characters.add(new CharacterData("Toad","Flota al saltar","Heroe del reino Champinon",R.drawable.toad_));

        characters.add(new CharacterData("Wario","SuperFuerza","El primo malvado de Mario",R.drawable.wario_));

        characters.add(new CharacterData("Bowser","Lanzallamas","El gran enemigo de Mario",R.drawable.bowser_));
    }

    @Override
    public void onStart(){
        super.onStart();
        if(getActivity() != null){
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.ch_list);
        }
    }
}
