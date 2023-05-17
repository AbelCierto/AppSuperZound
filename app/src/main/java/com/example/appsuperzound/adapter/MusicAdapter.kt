package com.example.appsuperzound.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.appsuperzound.R
import com.example.appsuperzound.database.MusicDB
import com.example.appsuperzound.models.Music
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class MusicAdapter(val musics: List<Music>, val context: Context)
    : Adapter<MusicAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvNameAlbum = view.findViewById<TextView>(R.id.tvNamAlbum)
        val tvNameArtist = view.findViewById<TextView>(R.id.tvArtista)
        val ivLogo = view.findViewById<ImageView>(R.id.ivLogo)
        val tvYear = view.findViewById<TextView>(R.id.tvYear)
        val tvScore = view.findViewById<TextView>(R.id.tvScore)
        val fabFavorite = view.findViewById<FloatingActionButton>(R.id.fabFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_music, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val music = musics[position]

        holder.tvNameAlbum.text = music.strAlbum
        holder.tvNameArtist.text = music.strArtist
        holder.tvYear.text = music.intYearReleased.toString()
        holder.tvScore.text = music.intScore.toString()

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build().load(music.strAlbumThumb)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.ivLogo)

        holder.fabFavorite.setOnClickListener{
            saveMusic(music)
        }
    }

    private fun saveMusic(music: Music) {
        if(music != null)
        {
            MusicDB.getInstance(this.context).getMusicDao().insertMusic(music)
        }
    }

    override fun getItemCount(): Int {
        return musics.size
    }

}