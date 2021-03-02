package com.gadrien456.gmail.tp3_adrien_guedes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gadrien456.gmail.tp3_adrien_guedes.NavigationListener
import com.gadrien456.gmail.tp3_adrien_guedes.R
import com.gadrien456.gmail.tp3_adrien_guedes.databinding.ActivityMainBinding
import com.gadrien456.gmail.tp3_adrien_guedes.di.DI
import com.gadrien456.gmail.tp3_adrien_guedes.ui.fragments.ListNeighborsFragment

class MainActivity :
    AppCompatActivity(),
    NavigationListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.inject(application)
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
