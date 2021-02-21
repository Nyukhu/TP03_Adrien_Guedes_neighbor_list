package com.gadrien456.gmail.tp3_adrien_guedes.data.datasource

import com.gadrien456.gmail.tp3_adrien_guedes.models.Neighbor

interface NeighborDatasource {
    /**
     * Get all my Neighbors
     * @return [List]
     */
    val neighbours: MutableList<Neighbor>

    /**
     * Deletes a neighbor
     * @param neighbor : Neighbor
     */
    fun deleteNeighbour(neighbor: Neighbor):Int

    /**
     * Create a neighbour
     * @param neighbor: Neighbor
     */
    fun createNeighbour(neighbor: Neighbor)

    /**
     * Update "Favorite status" of an existing Neighbour"
     * @param neighbor: Neighbor
     */
    fun updateFavoriteStatus(neighbor: Neighbor)

    /**
     * Update modified fields of an existing Neighbour"
     * @param neighbor: Neighbor
     */
    fun updateNeighbour(neighbor: Neighbor)
}

