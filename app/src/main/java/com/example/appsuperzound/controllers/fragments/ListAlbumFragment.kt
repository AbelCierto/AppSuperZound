package com.example.appsuperzound.controllers.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsuperzound.R
import com.example.appsuperzound.adapter.MusicAdapter
import com.example.appsuperzound.models.ApiResponseDetails
import com.example.appsuperzound.models.Music
import com.example.appsuperzound.network.MusicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListAlbumFragment : Fragment(){
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvListMusic)
        loadListMusic(view.context)
    }

    private fun loadListMusic(context: Context) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://theaudiodb.com/api/v1/json/523532/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val musicService: MusicService

        musicService = retrofit.create(MusicService::class.java)

        val request = musicService.getMusics()

        request.enqueue(object: Callback<ApiResponseDetails> {
            override fun onResponse(
                call: Call<ApiResponseDetails>,
                response: Response<ApiResponseDetails>
            ) {
                if (response.isSuccessful) {
                    val musics = response.body()!!.loved?:ArrayList()
                    recyclerView.adapter = MusicAdapter(musics, context)
                    recyclerView.layoutManager = LinearLayoutManager(context)
                }
                else{
                    Log.d("Activity Failed", "Error: " + response.code())
                }
            }
            override fun onFailure(call: Call<ApiResponseDetails>, t: Throwable) {
                Log.d("Activity Failed", "Error: " + t.toString())
            }
        })
    }
}