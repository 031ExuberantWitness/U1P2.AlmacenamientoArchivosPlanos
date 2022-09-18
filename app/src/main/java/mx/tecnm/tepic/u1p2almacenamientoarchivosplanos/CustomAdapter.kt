package mx.tecnm.tepic.u1p2almacenamientoarchivosplanos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception


class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    var nombres = ArrayList<String>()
    var apellidos = ArrayList<String>()
    var edades = ArrayList<String>()
    var generos = ArrayList<String>()
    var alturas = ArrayList<String>()
    var pesos = ArrayList<String>()

    var titles = arrayOf("Codelia")
    var details = arrayOf("Kotlin 01")


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemNombre.text = nombres[i]
        viewHolder.itemApellidos.text = apellidos[i]
        viewHolder.itemEdad.text = edades[i]
        viewHolder.itemGenero.text = generos[i]
        viewHolder.itemAltura.text = alturas[i]
        viewHolder.itemPeso.text = pesos[i]

        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDetail.text = details[i]
    }

    override fun getItemCount(): Int {
        return nombres.size
    }

    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var itemNombre: TextView
        var itemApellidos: TextView
        var itemEdad: TextView
        var itemGenero: TextView
        var itemAltura: TextView
        var itemPeso: TextView

        var itemTitle: TextView
        var itemDetail: TextView


        init{
            itemNombre = itemView.findViewById(R.id.item_nombre)
            itemApellidos = itemView.findViewById(R.id.item_apellidos)
            itemEdad = itemView.findViewById(R.id.item_edad)
            itemGenero = itemView.findViewById(R.id.item_genero)
            itemAltura = itemView.findViewById(R.id.item_altura)
            itemPeso = itemView.findViewById(R.id.item_peso)

            itemTitle = itemView.findViewById(R.id.item_nombre)
            itemDetail = itemView.findViewById(R.id.item_apellidos)
        }
    }

    fun abrirContenidoArchivo(){
        try {
            val archivo = this::class.java.getResourceAsStream("datos.txt")?.bufferedReader()?.readLines()
            /* Al incluir BufferedReader dentro de la creación del objeto "archivo", se le agrego
               un método de lecture de LINEA completa (cadena)
            */
            

        }catch (e: Exception){
        }
    }

}