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
        characters.add(new CharacterData(getString(R.string.mario),(getString(R.string.mario_skill)),
                (getString(R.string.mario_desc)),R.drawable.mario));

        characters.add(new CharacterData(getString(R.string.luigi),(getString(R.string.luigi_skill)),
                (getString(R.string.luigi_desc)),R.drawable.luigi));

        characters.add(new CharacterData(getString(R.string.peach),(getString(R.string.peach_skill)),
                (getString(R.string.peach_desc)),R.drawable.princesapeach));

        characters.add(new CharacterData(getString(R.string.yoshi),(getString(R.string.yoshi_skill)),
                (getString(R.string.yoshi_desc)),R.drawable.yoshi_));

        characters.add(new CharacterData(getString(R.string.toadette),(getString(R.string.toadette_skill)),
                (getString(R.string.toadette_desc)),R.drawable.toadette_));

        characters.add(new CharacterData(getString(R.string.toad),(getString(R.string.toadette_skill)),
                (getString(R.string.toad_desc)),R.drawable.toad_));

        characters.add(new CharacterData(getString(R.string.wario),(getString(R.string.wario_skill)),
                (getString(R.string.wario_desc)),R.drawable.wario_));

        characters.add(new CharacterData(getString(R.string.bowser),(getString(R.string.bowser_skill)),
                (getString(R.string.bowser_desc)),R.drawable.bowser_));
    }

    @Override
    public void onStart(){
        super.onStart();
        if(getActivity() != null){
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.ch_list);
        }
    }
}
