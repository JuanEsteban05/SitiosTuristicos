package com.example.sitiosturisticos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*
import com.example.sitiosturisticos.model.PoiItem
import com.squareup.picasso.Picasso

class DatosPoi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_poi)

        val buttonAtras = findViewById<ImageButton>(R.id.btnAtras)
        buttonAtras.setOnClickListener{
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val buttonConfig = findViewById<ImageButton>(R.id.imageButtonConfig)
        buttonConfig.setOnClickListener{
            intent = Intent(this, Notificaciones::class.java)
            startActivity(intent)
        }
        val tituloLugar : TextView = findViewById(R.id.textViewTituloP)
        val imagenTuristica : ImageView = findViewById(R.id.imageViewP)
        val descripcionLugar : TextView = findViewById(R.id.textViewDescripcionP)
        val puntuacionLugar : RatingBar = findViewById(R.id.ratingBarP)
        val puntuacionNum : TextView = findViewById(R.id.textViewCalificacion)
        val ubicacionLugar: TextView = findViewById(R.id.textViewUbicacion)
        val horarioLugar: TextView = findViewById(R.id.textViewHorario)


        val bundle : Bundle? = intent.extras


        val lugar: PoiItem = bundle?.getSerializable("datos") as PoiItem


        tituloLugar.text = lugar.titulo
        Picasso.get().load(lugar.imagen2).into(imagenTuristica)
        descripcionLugar.text = lugar.descripcionGeneral
        puntuacionLugar.rating = lugar.puntuacion.toFloat()
        ubicacionLugar.text = lugar.ubicacion
        horarioLugar.text = lugar.horario
        puntuacionNum.text = lugar.puntuacion.toString()
    }
/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_overflow,menu)
        return true
    }
*/

}