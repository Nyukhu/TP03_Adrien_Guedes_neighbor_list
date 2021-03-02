package com.gadrien456.gmail.tp3_adrien_guedes.models

import com.gadrien456.gmail.tp3_adrien_guedes.dal.room.entities.NeighborEntity

data class Neighbor(
    val id: Long,
    val name: String,
    val avatarUrl: String,
    val address: String,
    val phoneNumber: String,
    val aboutMe: String,
    val favorite: Boolean,
    val webSite: String
) {
    fun toEntity(): NeighborEntity {
        return NeighborEntity(id, name, avatarUrl, address, phoneNumber, aboutMe, favorite, webSite)
    }
}