package dam.pmdm.tareamariobros;

import static dam.pmdm.tareamariobros.R.drawable.ic_launcher_foreground;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.squareup.picasso.Picasso;

import dam.pmdm.tareamariobros.databinding.CharacterCardviewBinding;

public class CharacterViewHolder extends ViewHolder {
    private CharacterCardviewBinding binding;

    public CharacterViewHolder(CharacterCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    //vinculamos los datos con las tarjetas
    public void bind(CharacterData character) {
        //por cada elemento hay que definir el comportamiento del viewholder
        Picasso.get().load(character.getImage()).into(binding.imageCardview);
        binding.nameInCardview.setText(character.getName());
        binding.executePendingBindings();// aplicamos los cambios de inmediato

    }
}
