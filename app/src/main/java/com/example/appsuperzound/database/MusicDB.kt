package com.example.appsuperzound.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.appsuperzound.models.Music

@Database(entities = [Music::class], version = 1)
abstract class MusicDB: RoomDatabase() {
    abstract fun getMusicDao(): MusicDao
    companion object {
        private var INSTANCE: MusicDB? = null
        fun getInstance(context: Context): MusicDB {
            if (INSTANCE == null) {
                INSTANCE = Room
                        .databaseBuilder(context, MusicDB::class.java, "music.db")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE as MusicDB
        }
    }
}