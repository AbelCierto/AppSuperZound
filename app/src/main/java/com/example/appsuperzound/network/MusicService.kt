package com.example.appsuperzound.network

import com.example.appsuperzound.models.ApiResponseDetails
import retrofit2.Call
import retrofit2.http.GET

interface MusicService {
    @GET("mostloved.php?format=album")
    fun getMusics(): Call<ApiResponseDetails>

}