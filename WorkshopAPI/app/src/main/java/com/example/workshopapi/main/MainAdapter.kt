package com.example.workshopapi.main

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.workshopapi.R.id.movie_poster
import com.example.workshopapi.R.id.movie_title
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.workshopapi.BuildConfig.URL_POSTER
import com.example.workshopapi.R
import com.example.workshopapi.model.Movie
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainAdapter(private val result: List<Movie>, private val listener: (Movie)->Unit)
    :RecyclerView.Adapter<MovieViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int =result.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(result[position],listener)
    }
}
class MovieUI :AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout{
                lparams(width= matchParent, height= wrapContent)
                padding = dip(5)
                orientation =LinearLayout.VERTICAL

                imageView{
                    id = R.id.movie_poster
                }.lparams{
                    height= dip(250)
                    width= wrapContent
                }
                textView{
                    id = R.id.movie_title
                    textSize = 16f
                }.lparams{
                    margin = dip (16)

                }
            }
        }
    }
}

class MovieViewHolder(view: View) :RecyclerView.ViewHolder(view){
    private val moviePoster : ImageView = view.find(movie_poster)
    private val movieTitle : TextView = view.find (movie_title)

    fun bindItem(movies:Movie, listener: (Movie) -> Unit){
        Picasso.get().load(URL_POSTER+ movies.poster).into(moviePoster)
        Log.d("Gambar","url = "+ URL_POSTER+movies.poster)
        movieTitle.text= movies.title

        moviePoster.onClick {
            listener(movies)
        }

    }
}