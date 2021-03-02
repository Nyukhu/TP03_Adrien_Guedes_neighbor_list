package com.gadrien456.gmail.tp3_adrien_guedes.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.gadrien456.gmail.tp3_adrien_guedes.dal.NeighborDatasource
import com.gadrien456.gmail.tp3_adrien_guedes.dal.room.RoomNeighborDataSource
import com.gadrien456.gmail.tp3_adrien_guedes.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    val dataSource: NeighborDatasource

    init {
        dataSource = RoomNeighborDataSource(application)
    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbours(): LiveData<List<Neighbor>> = dataSource.neighbours

    fun delete(neighbor: Neighbor) = dataSource.deleteNeighbour(neighbor)
    fun createNeighbor(neighbor: Neighbor) = dataSource.createNeighbour(neighbor)

    companion object {
        private var instance: NeighborRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}
