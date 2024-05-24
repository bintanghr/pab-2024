package com.example.coffeeordering.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = MenuFragment()
        fragment.arguments = Bundle().apply {
            putInt(MenuFragment.ARG_SECTION_NUMBER, position)
        }
        return fragment
    }
}