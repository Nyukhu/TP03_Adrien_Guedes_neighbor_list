package com.gadrien456.gmail.tp3_adrien_guedes.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gadrien456.gmail.tp3_adrien_guedes.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>

    @Insert
    fun add(neighbor: NeighborEntity)
}
