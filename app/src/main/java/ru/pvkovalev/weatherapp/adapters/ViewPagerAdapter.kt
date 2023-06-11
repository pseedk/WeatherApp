package ru.pvkovalev.weatherapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val list: List<Fragment>) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = list.size

    override fun createFragment(position: Int) = list[position]
}