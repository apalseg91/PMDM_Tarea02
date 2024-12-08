package dam.pmdm.tareamariobros;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import dam.pmdm.tareamariobros.databinding.CharacterDetailFragmentBinding;

public class CharacterDetailFragment extends Fragment {
    private CharacterDetailFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //inflamos el layout para este fragment
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //obtenemos los datos que inician este fragmento
        if (getArguments() != null) {
            int image = getArguments().getInt("image");
            String name = getArguments().getString("name");
            String skill = getArguments().getString("skill");
            String description = getArguments().getString("description");

            //asignacion de datos a los componentes
            Picasso.get().load(image).into(binding.imageDetail);
            binding.nameDetail.setText(name);
            binding.skillDetail.setText(skill);
            binding.descriptionDetail.setText(description);

        }
    }

    @Override
    public void onStart(){
        super.onStart();
        if(getActivity() != null){
            //cambia titutlo del action bar
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.ch_details);
        }
    }

}
