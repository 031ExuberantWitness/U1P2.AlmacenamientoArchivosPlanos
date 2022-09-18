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
            guardarEnArchivo(binding.txtMensaje.text.toString())
        }

        binding.btnRecuperar.setOnClickListener {
            abrirContenidoArchivo()
        }

        binding.btnEliminar.setOnClickListener {
            resetEnArchivo("")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun resetEnArchivo(mensaje:String){
        try {
            var archivo = OutputStreamWriter(this.requireContext().openFileOutput("datos.txt",
                AppCompatActivity.MODE_PRIVATE
            ))

            archivo.write(mensaje)
            archivo.flush()
            archivo.close()

            Toast.makeText(context, "Mensaje actualizado", Toast.LENGTH_LONG).show()
            binding.txtMensaje.setText("")
        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun guardarEnArchivo(mensaje:String){
        try {
            var archivo = OutputStreamWriter(this.requireContext().openFileOutput("datos.txt",
                AppCompatActivity.MODE_PRIVATE
            ))

            archivo.write(mensaje)
            archivo.flush()
            archivo.close()

            Toast.makeText(context, "Mensaje actualizado", Toast.LENGTH_LONG).show()
            binding.txtMensaje.setText("")
        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun abrirContenidoArchivo(){
        try {
            var archivo = BufferedReader(InputStreamReader(this.requireContext().openFileInput("datos.txt")))
            /* Al incluir BufferedReader dentro de la creación del objeto "archivo", se le agrego
               un método de lecture de LINEA completa (cadena)
            */

            Toast.makeText(context, "Mensaje recuperado", Toast.LENGTH_LONG).show()
            binding.txtMensaje.setText((archivo.readLine()))
            archivo.close()
        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}