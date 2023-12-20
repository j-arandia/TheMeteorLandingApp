package com.jassikaa.themeteorlandingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.jassikaa.themeteorlandingapp.databinding.ActivityFavouritesBinding

class FavouritesActivity : AppCompatActivity() {
    lateinit var binding: ActivityFavouritesBinding
    private lateinit var recyclerViewManager: RecyclerView.LayoutManager
    val db = Firebase.firestore
    val nameKey:String = "NAME"
    val locKey:String = "LOC"

    val list: MutableList<API2Format> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // setup recyclerView
        recyclerViewManager = LinearLayoutManager(applicationContext)
        binding.recycler.layoutManager = recyclerViewManager
        binding.recycler.setHasFixedSize(true)


        //Displayang Data from the firestore
        db.collection("favourites")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Firestore", "${document.id} => ${document.data}")
                    Log.d("Firestore", document.data.get(locKey).toString())
                    val id = document.id
                    val name = document.data.get(nameKey).toString()
                    val loc = document.data.get(locKey).toString()
                    val meteor: API2Format = API2Format(id, name, loc)
                    list.add(meteor)
                }
                updateRecycler(list)
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting documents.", exception)
            }
    }

    fun updateRecycler(list: MutableList<API2Format>) {
        runOnUiThread {
            kotlin.run {
                //viewModel.updateInfo(intent.getStringExtra("search_key").toString())
                binding.recycler.adapter = RecyclerFavouriteAdapter(list)
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
}