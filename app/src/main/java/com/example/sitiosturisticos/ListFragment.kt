package com.example.sitiosturisticos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sitiosturisticos.databinding.FragmentListBinding
import com.example.sitiosturisticos.model.Poi
import com.example.sitiosturisticos.model.PoiItem
import com.google.gson.Gson

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var  lugares : ArrayList<PoiItem>
    private lateinit var listaPoi: Lista_poi


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       listBinding = FragmentListBinding.inflate(inflater, container, false)
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.hideIcon()

        lugares = loadMockLugaresFromJson()

        listaPoi = Lista_poi(lugares, onItemClicked = {onPoiClicked(it)})

        listBinding.lugaresRecycleView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter=listaPoi
            setHasFixedSize(false)
        }
        //recyclerView.adapter = adapter
    }

    private fun onPoiClicked(poi: PoiItem) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(poi))
    }

    private fun loadMockLugaresFromJson(): ArrayList<PoiItem> {

        var lugaresString: String = context?.assets?.open("sitiosTuristicos.json")?.bufferedReader().use{it!!.readText()}
        //lamda
        val gson = Gson()
        val lugar= gson.fromJson(lugaresString, Poi::class.java)

        return lugar

    }

    
}