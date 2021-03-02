package com.gadrien456.gmail.tp3_adrien_guedes.dal.memory

import androidx.lifecycle.MutableLiveData
import com.gadrien456.gmail.tp3_adrien_guedes.dal.InMemory_NeighborS
import com.gadrien456.gmail.tp3_adrien_guedes.dal.NeighborDatasource
import com.gadrien456.gmail.tp3_adrien_guedes.models.Neighbor

class InMemoryNeighborDataSource :
    NeighborDatasource {

    private val _neighbors = MutableLiveData<List<Neighbor>>()

    init {
        _neighbors.postValue(InMemory_NeighborS)
    }

    override val neighbours: MutableLiveData<List<Neighbor>>
        get() = _neighbors

    override fun deleteNeighbour(neighbor: Neighbor): Int {
        var indexToDelete = neighbours.value?.indexOf(neighbor)
        // neighbours.value?.remove(neighbor)

        if (indexToDelete == null)
            indexToDelete = -1

        return indexToDelete
    }

    override fun createNeighbour(neighbor: Neighbor) {
        InMemory_NeighborS.add(neighbor)
        neighbours.postValue(InMemory_NeighborS)
        // neighbours.value?.add(neighbor)
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    // Liste initial des voisins
}
