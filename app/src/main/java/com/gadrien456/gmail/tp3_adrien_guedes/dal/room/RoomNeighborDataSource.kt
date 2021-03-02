package com.gadrien456.gmail.tp3_adrien_guedes.dal.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.gadrien456.gmail.tp3_adrien_guedes.dal.NeighborDatasource
import com.gadrien456.gmail.tp3_adrien_guedes.dal.room.daos.NeighborDao
import com.gadrien456.gmail.tp3_adrien_guedes.dal.utils.toNeighbor
import com.gadrien456.gmail.tp3_adrien_guedes.models.Neighbor

class RoomNeighborDataSource(application: Application) : NeighborDatasource {
    private val database: NeighborDataBase = NeighborDataBase.getDataBase(application)
    private val dao: NeighborDao = database.neighborDao()

    private val _neighors = MediatorLiveData<List<Neighbor>>()

    init {
        _neighors.addSource(dao.getNeighbors()) { entities ->
            _neighors.value = entities.map { entity ->
                entity.toNeighbor()
            }
        }
    }

    override val neighbours: LiveData<List<Neighbor>>
        get() = _neighors

    override fun deleteNeighbour(neighbor: Neighbor): Int {
        TODO("Not yet implemented")
    }

    override fun createNeighbour(neighbor: Neighbor) {
        dao.add(neighbor.toEntity())
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}
