package mx.tecnm.tepic.u1p2almacenamientoarchivosplanos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.tepic.u1p2almacenamientoarchivosplanos.databinding.ActivityVistaTarjetasBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class VistaTarjetas : AppCompatActivity() {
    lateinit var binding: ActivityVistaTarjetasBinding
    var pacientes: ArrayList<pacienteInfo> = ArrayList()
    var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVistaTarjetasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.pacientes = ArrayList()

        for (pacientes in recuperarPacientes()) {
            var paciente = pacientes.split(",")
            this.pacientes.add(
                pacienteInfo(
                    paciente[0], paciente[1], paciente[2].toInt(),
                    paciente[3], paciente[4].toDouble(), paciente[5].toInt()
                )
            )
        }

        val recyclerView = this.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter(pacientes, object : CustomAdapter.onItemClick {
            override fun editClick(position: Int) {
                index = position

                binding.itemNombre.setText(pacientes.get(position).nombre)
                binding.itemApellidos.setText(pacientes.get(position).apellidos)
                binding.itemEdad.setText(pacientes.get(position).edad.toString())
                binding.itemGenero.setText(pacientes.get(position).genero)
                binding.itemAltura.setText(pacientes.get(position).altura.toString())
                binding.itemPeso.setText(pacientes.get(position).peso.toString())
            }

            override fun deleteClick(position: Int) {
                pacientes.removeAt(position)
                var mensaje: String = ""

                for (paciente in pacientes) {
                    mensaje += "${paciente.nombre},${paciente.apellidos}," +
                            "${paciente.edad},${paciente.genero}," +
                            "${paciente.altura},${paciente.peso}\n"
                }

                guardarEnArchivo(mensaje)

                binding.recyclerView.adapter?.notifyItemRemoved(position)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        binding.btnRegresar.setOnClickListener {
            finish()
        }

        binding.btnActualizar.setOnClickListener {
            var mensaje: String = ""
            pacientes.set(
                index, pacienteInfo(
                    binding.itemNombre.text.toString(),
                    binding.itemApellidos.text.toString(),
                    binding.itemEdad.text.toString().toInt(),
                    binding.itemGenero.text.toString(),
                    binding.itemAltura.text.toString().toDouble(),
                    binding.itemPeso.text.toString().toInt()
                )
            )

            for (paciente in pacientes) {
                mensaje += "${paciente.nombre},${paciente.apellidos}," +
                        "${paciente.edad},${paciente.genero}," +
                        "${paciente.altura},${paciente.peso}\n"
            }

            guardarEnArchivo(mensaje)
            binding.recyclerView.adapter?.notifyItemChanged(index)

            binding.itemNombre.setText("")
            binding.itemApellidos.setText("")
            binding.itemEdad.setText("")
            binding.itemGenero.setText("")
            binding.itemAltura.setText("")
            binding.itemPeso.setText("")
            index = 0
        }
    }

    fun guardarEnArchivo(mensaje: String) {
        try {
            var archivo = OutputStreamWriter(
                this.openFileOutput(
                    "datos.txt",
                    MODE_PRIVATE
                )
            )

            archivo.write(mensaje)
            archivo.flush()
            archivo.close()

            Toast.makeText(this, "List actualizada", Toast.LENGTH_LONG).show()
            //binding.txtMensaje.setText("")
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun recuperarPacientes(): List<String> {
        try {
            var archivo =
                BufferedReader(InputStreamReader(this.openFileInput("datos.txt")))

            Toast.makeText(this, "Pacientes recuperados", Toast.LENGTH_LONG).show()
            return archivo.readLines()
            archivo.close()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            return emptyList()
        }
    }
}