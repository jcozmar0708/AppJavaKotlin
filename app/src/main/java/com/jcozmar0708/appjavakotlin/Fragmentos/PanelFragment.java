package com.jcozmar0708.appjavakotlin.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jcozmar0708.appjavakotlin.BaseDatos.BasedeDatosMedia;
import com.jcozmar0708.appjavakotlin.Miscelanea.Alumno;
import com.jcozmar0708.appjavakotlin.Miscelanea.MiAdaptadorGrid;
import com.jcozmar0708.appjavakotlin.R;
import com.jcozmar0708.appjavakotlin.databinding.FragmentPanelBinding;

import java.util.List;

public class PanelFragment extends Fragment {

    private FragmentPanelBinding binding;
    private BasedeDatosMedia basedeDatosMedia;

    public PanelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPanelBinding.inflate(getLayoutInflater(), container, false);
        basedeDatosMedia = new BasedeDatosMedia(requireContext());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        List<Alumno> alumnos = basedeDatosMedia.obtenerAlumnos();

        if (alumnos != null) {
            MiAdaptadorGrid adapter = new MiAdaptadorGrid(requireContext(), R.layout.item_grid, alumnos);
            binding.idGridView.setAdapter(adapter);
        }
    }
}