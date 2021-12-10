package com.example.sitiosturisticos.detalle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sitiosturisticos.MainActivity
import com.example.sitiosturisticos.R
import com.example.sitiosturisticos.databinding.FragmentDetailBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private lateinit var detailBinding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        val sitio= args.poi

        val lugarMapa = LatLng((sitio.latitud).toDouble(), (sitio.longitud).toDouble())
        googleMap.addMarker(MarkerOptions().position(lugarMapa).title(sitio.titulo))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lugarMapa,17F))
    }

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
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        with(detailBinding) {
            textViewTituloP.text = lugar.titulo
            Picasso.get().load(lugar.imagen2).into(imageViewP)
            textViewDescripcionP.text = lugar.descripcionGeneral
            ratingBarP.rating = lugar.puntuacion.toFloat()
            textViewUbicacion.text = lugar.ubicacion
            textViewHorario.text = lugar.horario
            textViewCalificacion.text = lugar.puntuacion.toString()

            mapButton.setOnClickListener{
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToMapsFragment(lugar))
            }
        }
    }

}