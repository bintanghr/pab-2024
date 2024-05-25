package com.example.coffeeordering

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.coffeeordering.databinding.ActivityMainBinding
import com.example.coffeeordering.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isDarkMode = false

    companion object {
        private const val MODE = "mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val sharedPreferencesMode = getSharedPreferences(MODE, Context.MODE_PRIVATE)
        isDarkMode = sharedPreferencesMode.getBoolean("dark_mode", false)

        val item = navView.menu.findItem(R.id.action_dark_mode)
        if (isDarkMode) {
            item.icon = AppCompatResources.getDrawable(this, R.drawable.baseline_nightlight_24)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            item.icon = AppCompatResources.getDrawable(this, R.drawable.baseline_light_mode_24)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_menu,
                R.id.navigation_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener {item ->
            when (item.itemId) {
                R.id.action_dark_mode -> {
                    darkMode(item)
                    true
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_activity_main).navigateUp() || super.onSupportNavigateUp()
    }

    private fun darkMode(item: MenuItem) {
        isDarkMode = !isDarkMode

        val sharedPreferencesMode = getSharedPreferences(
            MODE, Context.MODE_PRIVATE)
        sharedPreferencesMode.edit().putBoolean("dark_mode", isDarkMode).apply()

        if (isDarkMode) {
            item.icon = AppCompatResources.getDrawable(this, R.drawable.baseline_nightlight_24)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            item.icon = AppCompatResources.getDrawable(this, R.drawable.baseline_light_mode_24)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        recreate()
    }

}