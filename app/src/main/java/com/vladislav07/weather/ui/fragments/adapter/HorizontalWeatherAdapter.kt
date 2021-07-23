package com.vladislav07.weather.ui.fragments.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vladislav07.weather.R
import com.vladislav07.weather.ui.model.WeatherUI

class HorizontalWeatherAdapter (
    private val listWeatherForDay: List<WeatherUI>
) : RecyclerView.Adapter<HorizontalWeatherAdapter.RecipeViewHolder>() {

    private val listWeather = listWeatherForDay

    class RecipeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time : TextView = itemView.findViewById(R.id.weatherTime)
        val temp: TextView = itemView.findViewById(R.id.temperature)
        val tempFeels : TextView = itemView.findViewById(R.id.temperatureFeels)
        val icon : ImageView = itemView.findViewById(R.id.weatherIcon)
        val description : TextView = itemView.findViewById(R.id.description)
        val windSpeed : TextView = itemView.findViewById(R.id.windSpeed)
        val humidity : TextView = itemView.findViewById(R.id.humidity)
        val pressure : TextView = itemView.findViewById(R.id.pressure)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.weather_horizontal_item, parent, false)
        val holder = RecipeViewHolder(itemView)
        return holder
    }

    override fun onBindViewHolder(
        holder: RecipeViewHolder,
        position: Int
    ) {
        holder.time.text = listWeather[position].time
        holder.temp.text = listWeather[position].temp
        holder.tempFeels.text = listWeather[position].feelsLike
        holder.description.text = listWeather[position].description
        holder.windSpeed.text = listWeather[position].windSpeed
        holder.humidity.text = listWeather[position].humidity
        holder.pressure.text = listWeather[position].pressure
        Picasso.get().load(listWeather[position].icon).into(holder.icon)
    }

    override fun getItemCount(): Int {
        return listWeather.size
    }

}