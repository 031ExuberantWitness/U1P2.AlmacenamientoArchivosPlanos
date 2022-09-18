package mx.tecnm.tepic.u1p2almacenamientoarchivosplanos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception
import java.util.ArrayList


class CustomAdapter(private val paciente: List<pacienteInfo>, private val onItemClickListener: onItemClick): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){


    interface onItemClick{
        fun editClick(position: Int)
        fun deleteClick(position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemNombre.text = paciente[i].nombre
        viewHolder.itemApellidos.text = paciente[i].apellidos
        viewHolder.itemEdad.text = paciente[i].edad.toString()
        viewHolder.itemGenero.text = paciente[i].genero
        viewHolder.itemAltura.text = paciente[i].altura.toString()
        viewHolder.itemPeso.text = paciente[i].peso.toString()
    }

    override fun getItemCount(): Int {
        return paciente.size
    }

    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var itemNombre: TextView
        var itemApellidos: TextView
        var itemEdad: TextView
        var itemGenero: TextView
        var itemAltura: TextView
        var itemPeso: TextView

        init{
            var btnModificar:ImageButton = itemview.findViewById(R.id.btnEdit)
            btnModificar.setOnClickListener {
                onItemClickListener.editClick(absoluteAdapterPosition)

            }

            var btnEliminar:ImageButton = itemview.findViewById(R.id.btnDelete)
            btnEliminar.setOnClickListener {
                onItemClickListener.deleteClick(absoluteAdapterPosition)

            }

            itemNombre = itemView.findViewById(R.id.item_nombre)
            itemApellidos = itemView.findViewById(R.id.item_apellidos)
            itemEdad = itemView.findViewById(R.id.item_edad)
            itemGenero = itemView.findViewById(R.id.item_genero)
            itemAltura = itemView.findViewById(R.id.item_altura)
            itemPeso = itemView.findViewById(R.id.item_peso)

        }
    }
}