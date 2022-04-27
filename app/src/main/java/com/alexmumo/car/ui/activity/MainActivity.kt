package com.alexmumo.car.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.alexmumo.car.R
import com.alexmumo.car.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navNavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navNavHostFragment.findNavController()
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.registerFragment,
                R.id.loginFragment,
                R.id.homeFragment
            ),
            binding.drawer
        )
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawer)
        NavigationUI.setupWithNavController(binding.navigationView, navController)
    }
    // up button is pressed
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }
}
