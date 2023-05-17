package com.example.appsuperzound.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "musics")
class Music (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @SerializedName("strAlbum")
    var strAlbum: String,

    @SerializedName("strArtist")
    var strArtist: String,

    @SerializedName("strAlbumThumb")
    var strAlbumThumb: String,

    @SerializedName("intYearReleased")
    var intYearReleased: Int,

    @SerializedName("intScore")
    var intScore: Float,

    @SerializedName("strAlbum3DCase")
    var strAlbum3DCase: String,

    @SerializedName("strGenre")
    var strGenre: String,

        ): Serializable