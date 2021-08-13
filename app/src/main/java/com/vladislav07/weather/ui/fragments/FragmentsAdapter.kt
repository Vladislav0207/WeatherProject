package com.vladislav07.weather.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vladislav07.weather.ui.fragments.start.ARG_OBJECT
import com.vladislav07.weather.ui.fragments.start.StartFragment

class FragmentsAdapter(fragment : FragmentActivity,  cityList: MutableList<String>) : FragmentStateAdapter(fragment) {
    private val list = cityList
    override fun getItemCount(): Int {
       return list.size
    }

    fun addTab(title: String){
        list.add(title)
        notifyDataSetChanged()
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = StartFragment()
        fragment.arguments = Bundle().apply {
            putString(ARG_OBJECT,list[position])
        }
        return fragment
    }
}