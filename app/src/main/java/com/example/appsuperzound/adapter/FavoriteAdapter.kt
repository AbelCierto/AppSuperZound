package com.example.appsuperzound.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.appsuperzound.R
import com.example.appsuperzound.database.MusicDB
import com.example.appsuperzound.models.Music
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class FavoriteAdapter(val musics: List<Music>, val context: Context): Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val ivLogo = view.findViewById<ImageView>(R.id.ivLogoFavorite)
        val tvName = view.findViewById<TextView>(R.id.tvAlbumFavorite)
        val tvGenero = view.findViewById<TextView>(R.id.tvGenero)
        val fabDelete = view.findViewById<FloatingActionButton>(R.id.fabDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val music = musics[position]
        holder.tvName.text = music.strAlbum
        holder.tvGenero.text = music.strGenre

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build().load(music.strAlbum3DCase)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.ivLogo)

        holder.fabDelete.setOnClickListener{
            DeleteMusic(music)
        }
    }

    override fun getItemCount(): Int {
        return musics.size
    }

    private fun DeleteMusic(music: Music){
        if(music != null)
        {
            MusicDB.getInstance(this.context).getMusicDao().deleteMusic(music)
        }
    }
}