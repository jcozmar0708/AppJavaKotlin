package com.jcozmar0708.appjavakotlin.Miscelanea

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.jcozmar0708.appjavakotlin.R
import com.jcozmar0708.appjavakotlin.databinding.ItemListBinding

class MiAdaptadorLista(
    private val context: Context,
    layout: Int,
    private val alumnos: List<Alumno>
) : ArrayAdapter<Alumno>(context, layout, alumnos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemListBinding
        val view: View

        if (convertView == null) {
            binding = ItemListBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as ItemListBinding
            view = convertView
        }

        val alumnoActual = alumnos[position]

        binding.textViewID.text = "ID: ${alumnoActual.id}"
        binding.textViewNombre.text = alumnoActual.nombre
        binding.textViewMedia.text = "Media: ${alumnoActual.media}"

        when {
            alumnoActual.media >= 5 -> binding.imageView.setImageResource(R.drawable.ic_aceptar)
            else -> binding.imageView.setImageResource(R.drawable.ic_cancelar)
        }

        return view
    }
}