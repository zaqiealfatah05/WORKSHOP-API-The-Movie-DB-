package com.example.workshopapi.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.workshopapi.detail.DetailActivity
import com.example.workshopapi.model.Movie
import com.example.workshopapi.network.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView


class MainActivity : AppCompatActivity(), MainView {


    private lateinit var listMovie : RecyclerView
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private var movies: MutableList <Movie> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(width= matchParent, height = wrapContent)
            orientation =LinearLayout.VERTICAL
            padding = dip(16)

            listMovie = recyclerView{
                lparams(width= matchParent,height = wrapContent)
                layoutManager = GridLayoutManager(ctx,2)
            }

        }
        adapter= MainAdapter(movies){
            startActivity<DetailActivity>(
                "TITLE" to it.title,
                "POSTER" to it.poster,
                "OVERVIEW" to it.overview
            )

        }
        listMovie.adapter=adapter
        presenter= MainPresenter(this, ApiRepository(), Gson())
        presenter.getMovieList()

        }
    override fun showMovieList(data: List<Movie>) {
        movies.clear()
        movies.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
