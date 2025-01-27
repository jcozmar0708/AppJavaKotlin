package com.jcozmar0708.appjavakotlin.Miscelanea;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jcozmar0708.appjavakotlin.R;
import com.jcozmar0708.appjavakotlin.databinding.ItemGridBinding;

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

    public static class ViewHolder {
        ImageView imageView;
        TextView textViewID;
        TextView textViewNombre;
        TextView textViewMedia;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.textViewID = convertView.findViewById(R.id.textViewID);
            holder.textViewNombre = convertView.findViewById(R.id.textViewNombre);
            holder.textViewMedia = convertView.findViewById(R.id.textViewMedia);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Alumno alumnoActual = alumnos.get(position);

        holder.textViewID.setText("ID: " + alumnoActual.getId());
        holder.textViewNombre.setText(alumnoActual.getNombre());
        holder.textViewMedia.setText("Media: " + alumnoActual.getMedia());

        if (alumnoActual.getMedia() >= 5) holder.imageView.setImageResource(R.drawable.ic_aceptar);
        else holder.imageView.setImageResource(R.drawable.ic_cancelar);

        return convertView;
    }
}
