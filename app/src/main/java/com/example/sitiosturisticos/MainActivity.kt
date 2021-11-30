package com.example.sitiosturisticos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sitiosturisticos.model.Poi
import com.example.sitiosturisticos.model.PoiItem
import com.google.gson.Gson

private lateinit var  lugares : ArrayList<PoiItem>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val buttonConfig = findViewById<ImageButton>(R.id.imageButtonConfig)
        buttonConfig.setOnClickListener{
            intent = Intent(this, Notificaciones::class.java)
            startActivity(intent)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.lugares_recycle_view)

        recyclerView.layoutManager = LinearLayoutManager(this)

        
        
        lugares = loadMockLugaresFromJson()

        val adapter = Lista_poi(lugares)
        recyclerView.adapter = adapter



    }

    private fun loadMockLugaresFromJson(): ArrayList<PoiItem> {
        var lugaresString: String = applicationContext.assets.open("sitiosTuristicos.json").bufferedReader().use{it.readText()}
        val gson = Gson()
        val lugar= gson.fromJson(lugaresString, Poi::class.java)

        return lugar



    }
/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater= menuInflater
        inflater.inflate(R.menu.menu_overflow,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val fm : FragmentManager = supportFragmentManager
        val ft : FragmentTransaction=fm.beginTransaction()

        return when (item.itemId){
            R.id.menu_preferencias -> {
                val fragmentSettings = SettingsFragment()
                ft.replace(R.id.fragmentContainerView,fragmentSettings).commit()
                true
            }else -> return true
        }
    }*/


}