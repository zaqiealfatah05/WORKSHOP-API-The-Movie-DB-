package com.example.workshopapi.network

import com.example.workshopapi.BuildConfig.API_KEY
import com.example.workshopapi.BuildConfig.BASE_URL

object TMDBApi {
    fun getMovie():String{
        return BASE_URL+ API_KEY
    }
}