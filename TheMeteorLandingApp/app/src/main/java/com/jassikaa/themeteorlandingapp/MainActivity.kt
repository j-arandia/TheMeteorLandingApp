package com.jassikaa.themeteorlandingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.jassikaa.themeteorlandingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ResultsViewModel

    lateinit var data: List<APIFormat>

    val db = Firebase.firestore
    val userKey:String = "USER"
    val todoKey:String = "TODO"

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        binding.textViewUser.text = intent.getStringExtra("userid")
        binding.textViewEmail.text = intent.getStringExtra("email")


        /*
        binding.name = ""
        binding.year = ""
        binding.mass = ""
        binding.url = ""
        binding.copyright = ""
        */





        viewModel = ViewModelProvider(this)[ResultsViewModel::class.java]


        // Observe changes in title
      /*  var nameObserver = Observer<String>{
                newValue ->
            binding.name = newValue
        }
        viewModel.name.observe(this,nameObserver)
*/
        //
        /*
        var yearObserver = Observer<String>{
                newValue ->
            binding.textViewEmail.text = newValue
        }
        viewModel.year.observe(this,yearObserver)
/*
        //
        var massObserver = Observer<String>{
                newValue ->
            binding.mass = newValue
        }
        viewModel.mass.observe(this,massObserver)

        //
        var reclatObserver = Observer<String>{
                newValue ->
            binding.reclat = newValue
        }
        viewModel.reclat.observe(this,reclatObserver)

        //
        var reclongObserver = Observer<String>{
                newValue ->
            binding.reclong = newValue
        }
        viewModel.reclong.observe(this,reclongObserver)


 */
        //
        var dataObserver = Observer<List<Meteor>>{
                newValue ->
            data = newValue
        }
        viewModel.meteorData.observe(this,dataObserver)

*/

        binding.buttonSearch.setOnClickListener {
            viewModel.onButtonGo(binding.editTextYear.text.toString())

            val intent = Intent(this,ResultsActivity::class.java)
            intent.putExtra("search_key", binding.editTextYear.text.toString())
            Log.d("TAG", "buttonSearch clicked")
            startActivity(intent)
            //startActivity(Intent(this,ResultsActivity::class.java))
            finish()
        }

    }//end onCreate



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