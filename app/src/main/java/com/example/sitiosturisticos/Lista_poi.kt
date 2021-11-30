package com.example.sitiosturisticos

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.RatingBar
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.sitiosturisticos.model.PoiItem
import com.squareup.picasso.Picasso

class Lista_poi ( private  val poiList :ArrayList<PoiItem>)
    : RecyclerView.Adapter<Lista_poi.ViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.activity_sitio_turistico,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lugar=poiList[position]
        holder.bind(lugar)
    }

    override fun getItemCount(): Int {
        return poiList.size
    }
    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        private var nameTextView: TextView = itemview.findViewById(R.id.textView_titulo)
        private var descripcionTextView: TextView = itemview.findViewById(R.id.textView_descripcion)
        private var puntajeTextView: RatingBar = itemview.findViewById(R.id.ratingBar2)
        private var pictureView: ImageView = itemview.findViewById(R.id.pictureImageView)
        fun bind(lugar: PoiItem){

            nameTextView.text=lugar.titulo
            descripcionTextView.text=lugar.pDescripcion
            puntajeTextView.rating= lugar.puntuacion.toFloat()
            Picasso.get().load(lugar.imagen).into(pictureView)



            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DatosPoi::class.java)
                val bundle = Bundle()

                bundle.putSerializable("datos", lugar)

                intent.putExtras(bundle)
                itemView.context.startActivity(intent)
            }
        }
    }
}