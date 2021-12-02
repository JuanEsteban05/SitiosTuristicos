package com.example.sitiosturisticos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.sitiosturisticos.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private lateinit var detailBinding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity?)?.showIcon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lugar = args.poi

        with(detailBinding) {
            textViewTituloP.text = lugar.titulo
            Picasso.get().load(lugar.imagen2).into(imageViewP)
            textViewDescripcionP.text = lugar.descripcionGeneral
            ratingBarP.rating = lugar.puntuacion.toFloat()
            textViewUbicacion.text = lugar.ubicacion
            textViewHorario.text = lugar.horario
            textViewCalificacion.text = lugar.puntuacion.toString()
        }
    }

}