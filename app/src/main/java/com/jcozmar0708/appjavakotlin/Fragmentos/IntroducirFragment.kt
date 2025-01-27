package com.jcozmar0708.appjavakotlin.Fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jcozmar0708.appjavakotlin.BaseDatos.BasedeDatosMedia
import com.jcozmar0708.appjavakotlin.Miscelanea.Alumno
import com.jcozmar0708.appjavakotlin.R
import com.jcozmar0708.appjavakotlin.databinding.FragmentIntroducirBinding

class IntroducirFragment : Fragment() {

    private lateinit var binding: FragmentIntroducirBinding
    private lateinit var basedeDatosMedia: BasedeDatosMedia

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroducirBinding.inflate(layoutInflater, container, false)
        basedeDatosMedia = BasedeDatosMedia(requireContext())

        binding.imageViewIntroducir.setImageResource(R.drawable.ic_aniadir)
        binding.editTextIDIntro.hint = getString(R.string.introduce_el_id)
        binding.editTextNombre.hint = getString(R.string.introduce_el_nombre)
        binding.editTextMates.hint = getString(R.string.introduce_la_nota_de_matematicas)
        binding.editTextLengua.hint = getString(R.string.introduce_la_nota_de_lengua)
        binding.editTextInformatica.hint = getString(R.string.introduce_la_nota_de_informatica)
        binding.editTextIngles.hint = getString(R.string.introduce_la_nota_de_ingles)

        binding.botonAceptarIntro.setImageResource(R.drawable.ic_aceptar)
        binding.botonCancelarIntro.setImageResource(R.drawable.ic_cancelar)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.botonCancelarIntro.setOnClickListener {
            findNavController().navigate(R.id.action_nav_introducir_to_inicioFragment)
        }

        binding.botonAceptarIntro.setOnClickListener {
            val idTexto = binding.editTextIDIntro.text.toString()
            val nombre = binding.editTextNombre.text.toString()
            val mates = binding.editTextMates.text.toString()
            val lengua = binding.editTextLengua.text.toString()
            val informatica = binding.editTextInformatica.text.toString()
            val ingles = binding.editTextIngles.text.toString()

            if (idTexto.isEmpty() || nombre.isEmpty() || mates.isEmpty() || lengua.isEmpty() || informatica.isEmpty() || ingles.isEmpty()) {
                Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val id = binding.editTextIDIntro.text.toString().toInt()
                val notaMates = binding.editTextMates.text.toString().toFloat()
                val notaLengua = binding.editTextLengua.text.toString().toFloat()
                val notaInformatica = binding.editTextInformatica.text.toString().toFloat()
                val notaIngles = binding.editTextIngles.text.toString().toFloat()
                val media = (notaMates + notaLengua + notaInformatica + notaIngles) / 4

                val alumno = Alumno(id, nombre, notaMates, notaLengua, notaInformatica, notaIngles, media)
                val resultado = basedeDatosMedia.introducirAlumno(alumno)

                if (resultado == -1L) {
                    Toast.makeText(requireContext(), "Error al insertar el alumno", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Alumno insertado correctamente", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}