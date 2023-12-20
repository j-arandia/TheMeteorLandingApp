package com.jassikaa.themeteorlandingapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val dataSet: List<APIFormat>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var metName: TextView
        var metMass: TextView
        //var metLocBtn: ImageView

        init {
            metName = view.findViewById(R.id.metName)
            metMass = view.findViewById(R.id.metMass)

            view.setOnClickListener {
                val context = itemView.context
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Get the latitude and longitude from your data set
                    val lat = dataSet[position].reclat// latitude
                    val lng = dataSet[position].reclong// longitude
                    val name = dataSet[position].name
                    val mass = dataSet[position].mass

                    // Create an Intent to start the new Activity
                    val intent = Intent(context, DetailsActivity::class.java).apply {
                        putExtra("LATITUDE", lat)
                        putExtra("LONGITUDE", lng)
                        putExtra("NAME", name)
                        putExtra("MASS", mass)
                        putExtra("POSITION", position)
                    }

                    // Start the new Activity
                    context.startActivity(intent)
                }
            }

        }
    }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_items, viewGroup, false)

            val lp = view.layoutParams
            lp.height = 256
            view.layoutParams = lp


            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.metName.text = dataSet[position].name
            viewHolder.metMass.text = dataSet[position].mass
            //viewHolder.metLocBtn.text = dataSet[position].description

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount():Int {
            return dataSet.size
        }

    }



