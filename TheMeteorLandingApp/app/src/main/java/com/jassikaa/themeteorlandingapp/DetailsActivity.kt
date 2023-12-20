package com.jassikaa.themeteorlandingapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.jassikaa.themeteorlandingapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var binding: ActivityDetailsBinding
    lateinit var viewModel: ResultsViewModel
    val position = LatLng(-33.920455, 18.466941)
    private lateinit var mMap: GoogleMap

    val db = Firebase.firestore
    val nameKey:String = "NAME"
    val locKey:String = "LOC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this)[ResultsViewModel::class.java]


        binding.mapBtn.setOnClickListener {
            val lat = intent.getStringExtra("LATITUDE").toString()
            val long = intent.getStringExtra("LONGITUDE").toString()
            //val context = itemView.context
            val url = "https://www.google.com/maps/search/?api=1&query=$lat,$long"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        updateUI()

        // Cloud Firestore, add data
        binding.buttonFavourites.setOnClickListener {
            Log.d("Firebase", "favourite meteorites")
            var meteorToSave:MutableMap<String,String> = mutableMapOf<String,String>()
            meteorToSave.put(nameKey, binding.textView.text.toString()) //name
            meteorToSave.put(locKey,"${binding.textViewLat.text.toString()}, ${binding.textViewLong.text.toString()}")

            // Version 1 create or add to users collection
            db.collection("favourites")
                .add(meteorToSave)
                .addOnSuccessListener {
                    Log.d("Firebase",binding.textView.text.toString() + " added successfully")
                    Toast.makeText(this,binding.textView.text.toString() + " added successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Log.d("Firebase", binding.textView.text.toString() + " not added")
                }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun buttonClickSearch(item: MenuItem) {
        startActivity(Intent(this,MainActivity::class.java))
    }

    fun buttonClickFavourites(item: MenuItem) {
        startActivity(Intent(this,FavouritesActivity::class.java))
    }

    fun buttonClickAbout(item: MenuItem) {
        startActivity(Intent(this,AboutActivity::class.java))
    }

    fun buttonClickLogout(item: MenuItem) {
        // logs out of Firebase
        FirebaseAuth.getInstance().signOut()
        Toast.makeText(this,R.string.logged_out, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    fun updateUI() {
        runOnUiThread {
            kotlin.run {
                val name = intent.getStringExtra("NAME").toString()
                val lat = intent.getStringExtra("LATITUDE").toString()
                val long = intent.getStringExtra("LONGITUDE").toString()
                val year = intent.getStringExtra("YEAR").toString()
                val googleLink = "https://maps.google.com/?q=${lat},${long}"
                binding.textView.text = name
                binding.textViewLat.text = "latitude: ${lat} "
                binding.textViewLong.text = "longitude: ${long}"
                binding.textViewGoogle.text = "Meteorite fell on this geolocation,"
                binding.textViewGoogle.setMovementMethod(LinkMovementMethod.getInstance())
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

}