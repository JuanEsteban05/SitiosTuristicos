package com.example.sitiosturisticos.lista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sitiosturisticos.Lista_poi
import com.example.sitiosturisticos.MainActivity
import com.example.sitiosturisticos.databinding.FragmentListBinding
import com.example.sitiosturisticos.model.Poi
import com.example.sitiosturisticos.model.PoiItem
import com.google.gson.Gson

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    //private lateinit var lugares: ArrayList<PoiItem>

    private var lugares : ArrayList<PoiItem> = arrayListOf()
    private lateinit var listaPoi: Lista_poi
    private lateinit var listViewModel: ListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]

        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity?)?.hideIcon()

        listViewModel.loadMockLugaresFromJson(context?.assets?.open("sitiosTuristicos.json"))

        listViewModel.onSitiosLoaded.observe(viewLifecycleOwner, { result ->
            onSitiosLoadedSubscribe(result)
        })

        listaPoi = Lista_poi(lugares, onItemClicked = { onPoiClicked(it) })

        listBinding.lugaresRecycleView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listaPoi
            setHasFixedSize(false)

        }
    }
        private fun onSitiosLoadedSubscribe(result: ArrayList<PoiItem>?) {
            result?.let { lugares ->
                listaPoi.appendItems(lugares)
            }

        }


        private fun onPoiClicked(poi: PoiItem) {
            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailFragment(
                    poi
                )
            )
        }



}