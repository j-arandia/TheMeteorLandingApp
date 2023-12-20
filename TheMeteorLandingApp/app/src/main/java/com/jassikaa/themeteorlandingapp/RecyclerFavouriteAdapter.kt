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

class RecyclerFavouriteAdapter(private val dataSet: MutableList<API2Format>) :
    RecyclerView.Adapter<RecyclerFavouriteAdapter.ViewHolder>(){


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var metName: TextView
        var metLoc: TextView
        //var metLocBtn: ImageView

        init {
            metName = view.findViewById(R.id.metName)
            metLoc = view.findViewById(R.id.metLoc)

        }
    }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_favourite_items, viewGroup, false)

            val lp = view.layoutParams
            lp.height = 256
            view.layoutParams = lp


            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.metName.text = dataSet[position].name
            viewHolder.metLoc.text = dataSet[position].loc
            //viewHolder.metLocBtn.text = dataSet[position].description

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount():Int {
            return dataSet.size
        }

    }



