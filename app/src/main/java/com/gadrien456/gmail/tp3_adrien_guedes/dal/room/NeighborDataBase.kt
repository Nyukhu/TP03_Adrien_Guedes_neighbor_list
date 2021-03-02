package com.gadrien456.gmail.tp3_adrien_guedes.dal.room

import android.app.Application
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gadrien456.gmail.tp3_adrien_guedes.dal.InMemory_NeighborS
import com.gadrien456.gmail.tp3_adrien_guedes.dal.room.daos.NeighborDao
import com.gadrien456.gmail.tp3_adrien_guedes.dal.room.entities.NeighborEntity
import java.util.concurrent.Executors

@Database(
    entities = [NeighborEntity::class],
    version = 1
)
abstract class NeighborDataBase : RoomDatabase() {
    abstract fun neighborDao(): NeighborDao

    companion object {
        private var instance: NeighborDataBase? = null
        fun getDataBase(application: Application): NeighborDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    application.applicationContext,
                    NeighborDataBase::class.java,
                    "neighbor_database.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d("item", "tatata")
                        insertFakeData()
                    }
                })
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }

        private fun insertFakeData() {
            Executors.newSingleThreadExecutor().execute {
                Log.d("item", InMemory_NeighborS.toString())
                InMemory_NeighborS.forEach {
                    Log.d("added", it.toString())
                    instance?.neighborDao()?.add(it.toEntity())
                }
            }
        }
    }
}
