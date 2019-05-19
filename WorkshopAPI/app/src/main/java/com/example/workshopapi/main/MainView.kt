package com.example.workshopapi.main

import com.example.workshopapi.model.Movie

interface MainView{
    fun showMovieList(data:List<Movie>)
}