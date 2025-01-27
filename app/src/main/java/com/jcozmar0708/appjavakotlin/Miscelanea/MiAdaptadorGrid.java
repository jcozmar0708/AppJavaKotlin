package com.jcozmar0708.appjavakotlin.Miscelanea;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jcozmar0708.appjavakotlin.R;
import com.jcozmar0708.appjavakotlin.databinding.ItemListBinding;

import java.util.List;

public class MiAdaptadorGrid extends ArrayAdapter<Alumno> {
    private Context context;
    private int layout;
    private List<Alumno> alumnos;

    public MiAdaptadorGrid(@NonNull Context context, int layout, @NonNull List<Alumno> alumnos) {
        super(context, layout, alumnos);
        this.context = context;
        this.layout = layout;
        this.alumnos = alumnos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemListBinding binding;
        View view;

        if (convertView == null) {
            binding = ItemListBinding.inflate(LayoutInflater.from(context), parent, false);
            view = binding.getRoot();
            view.setTag(binding.getRoot());
        } else {
            binding = (ItemListBinding) convertView.getTag();
            view = convertView;
        }

        Alumno alumnoActual = alumnos.get(position);

        binding.textViewID.setText("ID: " + alumnoActual.getId());
        binding.textViewNombre.setText(alumnoActual.getNombre());
        binding.textViewMedia.setText("Media: " + alumnoActual.getMedia());

        if (alumnoActual.getMedia() >= 5) binding.imageView.setImageResource(R.drawable.ic_aceptar);
        else binding.imageView.setImageResource(R.drawable.ic_cancelar);

        return view;
    }
}
