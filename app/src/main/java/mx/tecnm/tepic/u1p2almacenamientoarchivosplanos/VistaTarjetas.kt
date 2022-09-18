package mx.tecnm.tepic.u1p2almacenamientoarchivosplanos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.tepic.u1p2almacenamientoarchivosplanos.databinding.ActivityVistaTarjetasBinding

class VistaTarjetas : AppCompatActivity() {
    lateinit var binding: ActivityVistaTarjetasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVistaTarjetasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = this.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        binding.btnRegresar.setOnClickListener {
            finish()
        }
    }
}