package com.gadrien456.gmail.tp3_adrien_guedes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gadrien456.gmail.tp3_adrien_guedes.di.DI
import com.gadrien456.gmail.tp3_adrien_guedes.models.Neighbor
import com.gadrien456.gmail.tp3_adrien_guedes.repositories.NeighborRepository

class NeighborViewModel : ViewModel() {
    private val repository: NeighborRepository = DI.repository

    // On fait passe plat sur le résultat retourné par le repository
    val neighbors: LiveData<List<Neighbor>>
        get() = repository.getNeighbours()
}