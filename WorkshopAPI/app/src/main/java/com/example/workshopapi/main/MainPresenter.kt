package com.example.workshopapi.main

import com.example.workshopapi.model.MovieResponses
import com.example.workshopapi.network.ApiRepository
import com.example.workshopapi.network.TMDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView, private val apiRepository:ApiRepository, private val gson: Gson){
    fun getMovieList(){
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TMDBApi.getMovie()),
                MovieResponses::class.java)
            uiThread {
                view.showMovieList(data.results)
            }
        }
    }
}