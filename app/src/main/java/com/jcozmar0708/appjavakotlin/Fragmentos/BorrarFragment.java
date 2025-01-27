package com.jcozmar0708.appjavakotlin.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.jcozmar0708.appjavakotlin.BaseDatos.BasedeDatosMedia;
import com.jcozmar0708.appjavakotlin.R;
import com.jcozmar0708.appjavakotlin.databinding.FragmentBorrarBinding;

public class BorrarFragment extends Fragment {

    private FragmentBorrarBinding binding;
    private BasedeDatosMedia basedeDatosMedia;

    public BorrarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBorrarBinding.inflate(getLayoutInflater(), container, false);
        basedeDatosMedia = new BasedeDatosMedia(requireContext());

        binding.imageViewBorrar.setImageResource(R.drawable.ic_borrar);
        binding.editTextIDBorrar.setHint(R.string.introduce_el_id_del_alumno);

        binding.botonAceptarIntro.setImageResource(R.drawable.ic_aceptar);
        binding.botonCancelarIntro.setImageResource(R.drawable.ic_cancelar);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.botonCancelarIntro.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_borrarFragment_to_inicioFragment);
        });

        binding.botonAceptarIntro.setOnClickListener(v -> {
            String id = String.valueOf(binding.editTextIDBorrar.getText());

            if (id.isEmpty()) {
                Toast.makeText(requireContext(), "Rellena el campo", Toast.LENGTH_SHORT).show();
            } else {
                if (basedeDatosMedia.alumnoExiste(id)) {
                    int resultado = basedeDatosMedia.borrarAlumno(id);
                    if (resultado > 0) {
                        Toast.makeText(requireContext(), "Alumno borrado correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Error al borrar el alumno", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "El alumno no existe", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}