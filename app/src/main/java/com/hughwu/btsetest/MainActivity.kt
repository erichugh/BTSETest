package com.hughwu.btsetest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hughwu.btsetest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding?.root)
        setBottomNavigationView()
    }

    private fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    private fun setBottomNavigationView(){
        val navController = Navigation.findNavController(this, R.id.nav_host)
        binding?.bottomNavigation?.setupWithNavController(navController)
        binding?.bottomNavigation?.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.fragmentA ->{
                    findNavController(R.id.nav_host).navigate(R.id.fragmentA)
                }
                R.id.fragmentB ->{
                    findNavController(R.id.nav_host).navigate(R.id.fragmentB)
                }
                R.id.fragmentC ->{
                    findNavController(R.id.nav_host).navigate(R.id.fragmentC)
                }
                R.id.fragmentD ->{
                    findNavController(R.id.nav_host).navigate(R.id.fragmentD)
                }
            }
            true
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.settingFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }
    private fun showBottomNav() {
        binding?.bottomNavigation?.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding?.bottomNavigation?.visibility = View.GONE
    }
}