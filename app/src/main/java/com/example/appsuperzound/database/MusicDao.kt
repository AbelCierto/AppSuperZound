package com.example.appsuperzound.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appsuperzound.models.Music

@Dao
interface MusicDao {
    @Insert
    fun insertMusic(vararg music: Music)
    @Query("SELECT * FROM musics")
    fun getAllMusics(): List<Music>
    @Delete
    fun deleteMusic(vararg music: Music)
    @Update
    fun updateMusic(vararg music: Music)
}