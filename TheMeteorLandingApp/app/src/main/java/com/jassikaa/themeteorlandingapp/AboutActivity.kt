package com.jassikaa.themeteorlandingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.jassikaa.themeteorlandingapp.databinding.ActivityAboutBinding
import com.jassikaa.themeteorlandingapp.databinding.ActivityMainBinding

class AboutActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

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