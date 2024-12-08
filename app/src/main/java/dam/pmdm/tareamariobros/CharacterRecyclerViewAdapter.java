package dam.pmdm.tareamariobros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dam.pmdm.tareamariobros.databinding.CharacterCardviewBinding;

public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private ArrayList<CharacterData> characters;
    private Context context;

    public CharacterRecyclerViewAdapter(ArrayList<CharacterData> characters, Context context) {
        this.characters = characters;
        this.context = context;

    }

    //metodo que carga las cardview
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharacterCardviewBinding binding = CharacterCardviewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new CharacterViewHolder(binding);
    }

    //metodo que maneja una cardview concreta
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {

        CharacterData currentCharacter = this.characters.get(position);
        holder.bind(currentCharacter);

        //manejamos el evento click
        holder.itemView.setOnClickListener(view -> itemClicked(currentCharacter, view));
    }

    //devuelve el numero de items que tiene la lista
    @Override
    public int getItemCount() {
        return characters.size();
    }

    private void itemClicked(CharacterData currentCharacter, View view) {
        //llama a la funcion characterCliked del MainActivity, pasandole la vista
        ((MainActivity) context).characterClicked(currentCharacter, view);
        Toast.makeText(context, context.getText(R.string.toast_msg) + currentCharacter.getName(), Toast.LENGTH_SHORT).show();
    }
}
