package com.gadrien456.gmail.tp3_adrien_guedes.data

import com.gadrien456.gmail.tp3_adrien_guedes.data.datasource.InMemoryNeighborDataSource
import com.gadrien456.gmail.tp3_adrien_guedes.data.datasource.NeighborDatasource
import com.gadrien456.gmail.tp3_adrien_guedes.models.Neighbor

class NeighborRepository {
    val dataSource: NeighborDatasource = InMemoryNeighborDataSource()

    // On ne veut pas qu'on puisse instancier le repository de l'extérieur; pour cela on rend son constructeur private
    private constructor() {

    }

    // Quand le repository est instancié, on charge instance la datasource aussi
    init {

    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbours(): List<Neighbor> = dataSource.neighbours

    companion object {
        private var instance: NeighborRepository? = null
        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}