package com.vladislav07.weather.ui.fragments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vladislav07.weather.R
import com.vladislav07.weather.ui.model.WeatherUI

class VerticalWeatherAdapter(
    private val weatherMap : Map<Int,List<WeatherUI>>,
    private val context: Context?
) : RecyclerView.Adapter<VerticalWeatherAdapter.RecipeViewHolder>() {


    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.weatherDate)
        val horizontalRecyclerView: RecyclerView = itemView.findViewById(R.id.horizontalWeatherRecycler)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_vertical_item, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: RecipeViewHolder,
        position: Int
    ) {
        holder.date.text = weatherMap[position]?.get(0)?.date
        holder.horizontalRecyclerView.adapter= weatherMap[position]?.let {
            HorizontalWeatherAdapter(it)
        }
        holder.horizontalRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun getItemCount(): Int {
        return weatherMap.size
    }

}