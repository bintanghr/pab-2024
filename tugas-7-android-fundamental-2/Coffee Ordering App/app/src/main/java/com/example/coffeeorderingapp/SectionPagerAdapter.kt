package com.example.coffeeorderingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
//        var fragment: Fragment? = null
//        when (position) {
//            0 -> fragment = BeveragesFragment()
//            1 -> fragment = BreadFragment()
//        }
//        return fragment as Fragment

        val fragment = BeveragesFragment()
        fragment.arguments = Bundle().apply {
            putInt(BeveragesFragment.ARG_SECTION_NUMBER, position)
        }
        return fragment
    }
}