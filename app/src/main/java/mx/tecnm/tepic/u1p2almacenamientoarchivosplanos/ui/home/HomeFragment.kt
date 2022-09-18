package mx.tecnm.tepic.u1p2almacenamientoarchivosplanos.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.u1p2almacenamientoarchivosplanos.VistaTarjetas
import mx.tecnm.tepic.u1p2almacenamientoarchivosplanos.databinding.FragmentHomeBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnVisualizar.setOnClickListener {
            var otraVentana = Intent(context, VistaTarjetas::class.java)
            startActivity(otraVentana)
        }

        binding.btnGuardar.setOnClickListener {
            if (binding.itemNombre.text.isBlank() || binding.itemApellidos.text.isBlank() ||
                binding.itemEdad.text.isBlank() || binding.itemGenero.text.isBlank() ||
                binding.itemAltura.text.isBlank() || binding.itemPeso.text.isBlank()
            ) {
                Toast.makeText(
                    context,
                    "Campo de texto vacio, revise los campos",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var constructMensaje =
                    "${binding.itemNombre.text.toString()},${binding.itemApellidos.text.toString()}," +
                            "${binding.itemEdad.text.toString()},${binding.itemGenero.text.toString()}," +
                            "${binding.itemAltura.text.toString()},${binding.itemPeso.text.toString()}\n"
                guardarEnArchivo(constructMensaje)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun guardarEnArchivo(mensaje: String) {
        try {
            var archivo = OutputStreamWriter(
                this.requireContext().openFileOutput(
                    "datos.txt",
                    AppCompatActivity.MODE_APPEND
                )
            )

            archivo.append(mensaje)
            archivo.flush()
            archivo.close()

            binding.itemNombre.setText("")
            binding.itemApellidos.setText("")
            binding.itemEdad.setText("")
            binding.itemGenero.setText("")
            binding.itemAltura.setText("")
            binding.itemPeso.setText("")

            Toast.makeText(context, "Mensaje actualizado", Toast.LENGTH_LONG).show()
            //binding.txtMensaje.setText("")
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun abrirContenidoArchivo() {
        try {
            var archivo =
                BufferedReader(InputStreamReader(this.requireContext().openFileInput("datos.txt")))

            Toast.makeText(context, archivo.readLine(), Toast.LENGTH_LONG).show()
            archivo.close()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}