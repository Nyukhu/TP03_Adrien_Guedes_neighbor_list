package com.gadrien456.gmail.tp3_adrien_guedes.dal.utils

import com.gadrien456.gmail.tp3_adrien_guedes.dal.room.entities.NeighborEntity
import com.gadrien456.gmail.tp3_adrien_guedes.models.Neighbor

fun NeighborEntity.toNeighbor() = Neighbor(
    id = id,
    name = name,
    avatarUrl = avatarUrl,
    address = address,
    phoneNumber = phoneNumber,
    aboutMe = aboutMe,
    favorite = favorite,
    webSite = webSite ?: ""
)
