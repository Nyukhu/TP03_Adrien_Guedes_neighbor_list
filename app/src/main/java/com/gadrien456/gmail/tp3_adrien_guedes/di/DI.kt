package com.gadrien456.gmail.tp3_adrien_guedes.di

import android.app.Application
import com.gadrien456.gmail.tp3_adrien_guedes.repositories.NeighborRepository

object DI {
    lateinit var repository: NeighborRepository
    fun inject(application: Application) {
        repository = NeighborRepository.getInstance(application)
    }
}