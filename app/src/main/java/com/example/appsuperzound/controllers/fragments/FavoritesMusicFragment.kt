package com.example.appsuperzound.controllers.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsuperzound.R
import com.example.appsuperzound.adapter.FavoriteAdapter
import com.example.appsuperzound.database.MusicDB
import com.example.appsuperzound.models.Music

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavoritesMusicFragment : Fragment() {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_music, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvFavoritesList)
        loadFavorites(view.context)
    }

    private fun loadFavorites(context: Context) {
        val musics = MusicDB.getInstance(context).getMusicDao().getAllMusics() as ArrayList<Music>
        recyclerView.adapter = FavoriteAdapter(musics, context)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

}