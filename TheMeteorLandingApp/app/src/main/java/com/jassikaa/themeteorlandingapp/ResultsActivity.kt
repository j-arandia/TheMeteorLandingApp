package com.jassikaa.themeteorlandingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.jassikaa.themeteorlandingapp.databinding.ActivityResultsBinding

class ResultsActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultsBinding
    lateinit var viewModel: ResultsViewModel
    private lateinit var recyclerViewManager: RecyclerView.LayoutManager

    lateinit var data: List<APIFormat>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_results)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // setup recyclerView
        recyclerViewManager = LinearLayoutManager(applicationContext)
        binding.recycler.layoutManager = recyclerViewManager
        binding.recycler.setHasFixedSize(true)

        viewModel = ViewModelProvider(this)[ResultsViewModel::class.java]
        updateUI()


        var countObserver = Observer<Int>{
                newValue ->
            binding.countBlurb = "$newValue meteorites detected!"
        }
        viewModel.count.observe(this,countObserver)

        var yearObserver = Observer<String>{
                newValue ->
            binding.yearBlurb = "In $newValue there were..."
        }
        viewModel.year.observe(this,yearObserver)


        var dataObserver = Observer<List<APIFormat>>{
                newValue ->
            updateRecycler(newValue)
        }
        viewModel.data.observe(this,dataObserver)

    }


    fun updateRecycler(list: List<APIFormat>) {
        runOnUiThread {
            kotlin.run {
                viewModel.updateInfo(intent.getStringExtra("search_key").toString())
                binding.recycler.adapter = RecyclerAdapter(list)
            }
        }
    }
    fun updateUI() {
        runOnUiThread {
            kotlin.run {
                viewModel.updateInfo(intent.getStringExtra("search_key").toString())
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