package com.example.sitiosturisticos.lista

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sitiosturisticos.model.Poi
import com.example.sitiosturisticos.model.PoiItem
import com.google.gson.Gson
import java.io.InputStream

class ListViewModel : ViewModel() {

    private var sitiosLoad : MutableLiveData<ArrayList<PoiItem>> = MutableLiveData()
    val onSitiosLoaded : LiveData<ArrayList<PoiItem>> = sitiosLoad

    fun loadMockLugaresFromJson(lugaresString: InputStream?) {

        val lugaresString = lugaresString?.bufferedReader().use{it!!.readText()}
        val gson = Gson()
        sitiosLoad.value= gson.fromJson(lugaresString, Poi::class.java)

    }
}