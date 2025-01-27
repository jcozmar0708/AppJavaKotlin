package com.jcozmar0708.appjavakotlin.Fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jcozmar0708.appjavakotlin.BaseDatos.BasedeDatosMedia
import com.jcozmar0708.appjavakotlin.Miscelanea.MiAdaptadorLista
import com.jcozmar0708.appjavakotlin.R
import com.jcozmar0708.appjavakotlin.databinding.FragmentListarBinding

class ListarFragment : Fragment() {

    private lateinit var binding: FragmentListarBinding
    private lateinit var basedeDatosMedia: BasedeDatosMedia

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListarBinding.inflate(layoutInflater, container, false)
        basedeDatosMedia = BasedeDatosMedia(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val alumnos = basedeDatosMedia.obtenerAlumnos()

        if (alumnos != null) {
            val adapter = MiAdaptadorLista(requireContext(), R.layout.item_list, alumnos)
            binding.idListView.adapter = adapter
        }
    }
}