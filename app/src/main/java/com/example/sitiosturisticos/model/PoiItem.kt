package com.example.sitiosturisticos.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PoiItem(
    @SerializedName("descripcionGeneral")
    val descripcionGeneral: String,
    @SerializedName("horario")
    val horario: String,
    @SerializedName("imagen")
    val imagen: String,
    @SerializedName("imagen2")
    val imagen2: String,
    @SerializedName("pDescripcion")
    val pDescripcion: String,
    @SerializedName("puntuacion")
    val puntuacion: Double,
    @SerializedName("titulo")
    val titulo: String,
    @SerializedName("ubicacion")
    val ubicacion: String
):Serializable{
}