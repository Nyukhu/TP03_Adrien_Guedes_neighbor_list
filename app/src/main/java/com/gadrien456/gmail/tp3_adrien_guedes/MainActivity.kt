package com.gadrien456.gmail.tp3_adrien_guedes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gadrien456.gmail.tp3_adrien_guedes.databinding.ActivityMainBinding
import com.gadrien456.gmail.tp3_adrien_guedes.fragments.ListNeighborsFragment

class MainActivity : AppCompatActivity(),NavigationListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = binding.toolbar
        showFragment(ListNeighborsFragment())
    }


    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }
    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }
}